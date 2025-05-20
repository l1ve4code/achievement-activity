package ru.live4code.achievement_activity.achievement_service;

import com.google.gson.Gson;
import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;
import io.javalin.http.Context;

import ru.live4code.achievement_activity.achievement_service.dao.AchievementsDao;
import ru.live4code.achievement_activity.achievement_service.helper.AsyncThread;
import ru.live4code.achievement_activity.achievement_service.message.ActivityConsumer;

import java.util.List;

public class AchievementApplication {

    private static final Gson GSON = new Gson();

    public static void main(String[] args) {
        Javalin.create((config) -> {
            config.router.apiBuilder(() -> {
                ApiBuilder.path("/achievements", (() -> {
                    ApiBuilder.get("/get", AchievementApplication.Queries::achievementsGetQuery);
                }));
            });
        }).start(8081);

        AsyncThread.doAsynchronously(AchievementsDao.INSTANCE::giveAchievements);
        AsyncThread.doAsynchronously(Initialization.activityConsumer()::consume);
    }

    static class Queries {
        private static void achievementsGetQuery(Context context) {
            Models.UserId request = context.bodyAsClass(Models.UserId.class);
            List<String> userAchievements = AchievementsDao.INSTANCE.getAchievements(request.userId());
            context.result(GSON.toJson(userAchievements));
        }
    }

    static class Initialization {
        private static ActivityConsumer activityConsumer() {
            return new ActivityConsumer();
        }
    }

    static class Models {
        private record UserId(long userId) {
        }
    }

}
