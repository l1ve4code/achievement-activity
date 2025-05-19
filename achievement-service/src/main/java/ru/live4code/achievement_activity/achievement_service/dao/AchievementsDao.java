package ru.live4code.achievement_activity.achievement_service.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import static ru.live4code.achievement_activity.achievement_service.config.ApplicationConfiguration.namedParameterJdbcTemplate;

public class AchievementsDao {

    public static final AchievementsDao INSTANCE = new AchievementsDao();

    public void upsertUserAction(long userId, String action) {
        namedParameterJdbcTemplate().update("""
                insert into public.statistics
                    (user_id, action, amount)
                values
                    (:user_id, :action, :amount)
                on conflict (user_id, action) do update
                set amount = statistics.amount + 1
                """,
                new MapSqlParameterSource()
                        .addValue("user_id", userId)
                        .addValue("action", action)
                        .addValue("amount", 1L)
        );
    }

}
