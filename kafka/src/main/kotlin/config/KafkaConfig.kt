package config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.LoggerFactory

import java.util.Properties

class KafkaConfig(private val host: String, private val port: Int,  private val groupId: String,  ) {

    val LOG = LoggerFactory.getLogger("config")

    fun init(): Properties  = Properties().apply {
        LOG.info("Connecting to $host:$port, Group: $groupId, with De/Serializer ${StringDeserializer::class.qualifiedName} ${StringSerializer::class.qualifiedName}")
        put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "$host:$port")
        put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
        put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer())
        put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer())
    }
}