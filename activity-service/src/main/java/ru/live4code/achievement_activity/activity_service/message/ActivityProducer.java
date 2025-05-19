package ru.live4code.achievement_activity.activity_service.message;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;

import ru.live4code.achievement_activity.activity_service.config.ApplicationConfiguration;
import ru.live4code.achievement_activity.activity_service.converter.ProtoSerializer;
import ru.live4code.achievement_activity.activity_service.generated.ActivityProtoMessage;

import java.util.Properties;

public class ActivityProducer {

    private final Producer<Integer, ActivityProtoMessage.UserAction> producer;

    public ActivityProducer() {
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, ApplicationConfiguration.KAFKA_BOOTSTRAP_SERVER);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, ApplicationConfiguration.KAFKA_ACTIVITY_PRODUCER);

        this.producer = new KafkaProducer<>(properties, new IntegerSerializer(), new ProtoSerializer());
    }

    public void produce(ActivityProtoMessage.UserAction userAction) {
        producer.send(new ProducerRecord<>(ApplicationConfiguration.KAFKA_ACTIVITY_TOPIC, 0, userAction));
    }

}
