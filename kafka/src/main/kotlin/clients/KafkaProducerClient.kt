package clients

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import java.util.*

class KafkaProducerClient(config: Properties){

    private val producer = KafkaProducer<String, String>(config)
    private val LOG = LoggerFactory.getLogger(KafkaProducerClient::class.java)

    init {
        LOG.info("Connecting to ${config.entries.joinToString()}")
    }

    fun send(key:String, value:String, topics: String){
        val record = ProducerRecord(topics, key, value)
        producer.send(record)
    }

    fun close() {
        producer.close()
    }
}