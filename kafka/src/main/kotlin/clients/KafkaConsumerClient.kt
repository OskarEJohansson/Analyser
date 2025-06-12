package clients

import coder.JsonCoder
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.slf4j.LoggerFactory
import java.time.Duration
import java.util.Properties
import kotlin.reflect.KClass


class KafkaConsumerClient<T: Any>(
    topic: String,
    config: Properties,
    private val coder: JsonCoder = JsonCoder(),
    private val targetClass: KClass<T>,){

    private val LOG = LoggerFactory.getLogger(KafkaConsumerClient::class.java)
    private val consumer = KafkaConsumer<String,String>(config)

    init {
        LOG.info("KafkaConsumer connecting to $topic, properties = ${config.entries.joinToString()}")
        consumer.subscribe(listOf(topic))
    }

    fun pollAndProcess(handler: (ConsumerRecord<String, T>) -> Unit) {
        val records = consumer.poll(Duration.ofMillis(100))
        records.forEach { record ->
            val deserialized = coder.deserialize(record.value(), targetClass)
            handler(
                ConsumerRecord(
                    record.topic(),
                    record.partition(),
                    record.offset(),
                    record.key(),
                    deserialized,
                )
            )
        }
    }

    fun close(){
        consumer.close()
    }
}