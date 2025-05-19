package ru.live4code.achievement_activity.activity_service.converter;

import org.apache.kafka.common.serialization.Serializer;

import ru.live4code.achievement_activity.activity_service.generated.ActivityProtoMessage;

public class ProtoSerializer implements Serializer<ActivityProtoMessage.UserAction> {

    @Override
    public byte[] serialize(String s, ActivityProtoMessage.UserAction userAction) {
        return userAction.toByteArray();
    }

}
