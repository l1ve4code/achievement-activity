package ru.live4code.achievement_activity.achievement_service;

import ru.live4code.achievement_activity.achievement_service.message.ActivityConsumer;

public class AchievementApplication {

    public static void main(String[] args) {
        Initialization.activityConsumer().consume();
    }

    static class Initialization {
        private static ActivityConsumer activityConsumer() {
            return new ActivityConsumer();
        }
    }

}
