package ru.live4code.achievement_activity.achievement_service.helper;

public class AsyncThread {
    public static void doAsynchronously(Runnable task) {
        new Thread(() -> {
            while(true) {
                task.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
