import clients.KafkaConsumerClient


import config.KafkaConfig
import model.kafka.TestEvent
import org.slf4j.LoggerFactory

fun main() {

    val config = KafkaConfig("localhost", 9092, "test").init()
    val consumer = KafkaConsumerClient(topic = "test", config = config, targetClass = TestEvent::class)
    val LOG = LoggerFactory.getLogger("main")
    LOG.info("Starting Kafka consumer")

    while(true){
        consumer.pollAndProcess {
            record -> LOG.info("Received ${record.value()} from topic ${record.topic()}")
        }
    }
}