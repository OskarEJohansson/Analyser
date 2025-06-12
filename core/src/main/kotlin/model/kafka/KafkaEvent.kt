package model.kafka

interface KafkaEvent {
    val type: DomainEventType
}

enum class DomainEventType {
    TEST_EVENT
}