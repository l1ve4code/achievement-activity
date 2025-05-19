package ru.live4code.achievement_activity.achievement_service.converter;

import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.kafka.common.serialization.Deserializer;

import ru.live4code.achievement_activity.activity_service.generated.ActivityProtoMessage;

public class ProtoDeserializer implements Deserializer<ActivityProtoMessage.UserAction> {

    @Override
    public ActivityProtoMessage.UserAction deserialize(String s, byte[] bytes) {
        try {
            return ActivityProtoMessage.UserAction.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

}
