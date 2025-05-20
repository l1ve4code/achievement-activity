package ru.live4code.achievement_activity.achievement_service.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.Collections;
import java.util.List;

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

    public List<String> getAchievements(long userId) {
        return namedParameterJdbcTemplate().query("""
                select achievement_name
                from public.given_achievements
                where user_id = :userId
                """,
                new MapSqlParameterSource("userId", userId),
                (rs, __) -> rs.getString("achievement_name")
        );
    }

    public void giveAchievements() {
        namedParameterJdbcTemplate().update("""
                with achievement_to_give as (
                    select s.user_id, aa.name
                    from public.all_achievements aa
                    join public.statistics s using(action)
                    left join public.given_achievements ga
                        on aa.name = ga.achievement_name
                    where aa.condition <= s.amount
                        and ga.user_id is null
                )
                insert into public.given_achievements
                    (user_id, achievement_name)
                select user_id, name
                from achievement_to_give
                """,
                Collections.emptyMap());
    }

}
