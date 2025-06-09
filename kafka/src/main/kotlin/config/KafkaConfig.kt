package config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import java.util.Properties

class KafkaConfig(private val host: String, private val port: Int,  private val groupId: String,  ) {

    fun init(): Properties  = Properties().apply {
        put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "$host:$port")
        put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
    }
}

//         consumer specific config
//         put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
//         put(ConsumerConfig.GROUP_ID_CONFIG, groupId)