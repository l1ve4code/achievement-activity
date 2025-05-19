package ru.live4code.achievement_activity.achievement_service.message;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;

import ru.live4code.achievement_activity.achievement_service.config.ApplicationConfiguration;
import ru.live4code.achievement_activity.achievement_service.converter.ProtoDeserializer;
import ru.live4code.achievement_activity.achievement_service.dao.AchievementsDao;
import ru.live4code.achievement_activity.activity_service.generated.ActivityProtoMessage;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class ActivityConsumer {

    private final Consumer<Integer, ActivityProtoMessage.UserAction> consumer;

    public ActivityConsumer() {
        Properties properties = new Properties();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, ApplicationConfiguration.KAFKA_BOOTSTRAP_SERVER);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, ApplicationConfiguration.KAFKA_ACTIVITY_CONSUMER);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Boolean.TRUE);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);

        this.consumer = new KafkaConsumer<>(properties, new IntegerDeserializer(), new ProtoDeserializer());
        this.consumer.subscribe(List.of(ApplicationConfiguration.KAFKA_ACTIVITY_TOPIC));
    }

    public void consume() {
        while (true) {
            var records = consumer.poll(Duration.ofMillis(100));
            for (var record : records) {
                AchievementsDao.INSTANCE.upsertUserAction(record.value().getUserId(), record.value().getAction());
            }
        }
    }

}
