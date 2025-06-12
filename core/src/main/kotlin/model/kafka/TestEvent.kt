package model.kafka

data class TestEvent(override val type: DomainEventType = DomainEventType.TEST_EVENT, val  testData: String): KafkaEvent {
}