package ru.live4code.achievement_activity.activity_service;

import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.http.Context;

import org.jetbrains.annotations.NotNull;

import ru.live4code.achievement_activity.activity_service.generated.ActivityProtoMessage;
import ru.live4code.achievement_activity.activity_service.message.ActivityProducer;

public class ActivityApplication {

    public static void main(String[] args) {
        Javalin.create((config) -> {
            config.router.apiBuilder(() -> {
                ApiBuilder.path("/activity", (() -> {
                    ApiBuilder.post("/save", ActivityApplication.Queries::activitySaveQuery);
                }));
            });
        }).start(8080);
    }

    static class Queries {
        private static void activitySaveQuery(Context context) {
            Models.UserActivity userActivity = context.bodyAsClass(Models.UserActivity.class);
            Initialization.activityProducer().produce(ActivityProtoMessage.UserAction.newBuilder()
                    .setUserId(userActivity.userId)
                    .setAction(userActivity.action)
                    .build());
            context.result(Helpers.asJson("saved"));
        }
    }

    static class Helpers {
        private static String asJson(@NotNull String message) {
            return String.format("{\"result\": \"%s\"}", message);
        }
    }

    static class Initialization {
        private static ActivityProducer activityProducer() {
            return new ActivityProducer();
        }
    }

    static class Models {
        private record UserActivity(long userId, @NotNull String action) {
        }
    }

}
