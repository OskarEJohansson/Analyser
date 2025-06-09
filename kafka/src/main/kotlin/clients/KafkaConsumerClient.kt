package clients

import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.Properties


class KafkaConsumerClient(
    config: Properties,
    topics:List<String>){

    private val consumer = KafkaConsumer<String,String>(config)

    init {
        consumer.subscribe(topics)
    }

    fun close(){
        consumer.close()
    }
}