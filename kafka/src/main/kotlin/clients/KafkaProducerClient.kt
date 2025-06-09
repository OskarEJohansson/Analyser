package clients

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.*

class KafkaProducerClient(config: Properties){

        private val producer = KafkaProducer<String, String>(config)

    fun send(key:String, value:String, topics: String){
        val record = ProducerRecord(topics, key, value)
        producer.send(record)
    }

    fun close() {
        producer.close()
    }
}