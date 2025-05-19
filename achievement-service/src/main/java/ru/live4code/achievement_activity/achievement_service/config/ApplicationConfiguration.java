package ru.live4code.achievement_activity.achievement_service.config;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ApplicationConfiguration {

    public static final String KAFKA_BOOTSTRAP_SERVER = "localhost:29092";
    public static final String KAFKA_ACTIVITY_TOPIC = "javalin-activity-topic";
    public static final String KAFKA_ACTIVITY_CONSUMER = "javalin-activity-consumer";

    private static final DriverManagerDataSource dataSource;

    public static NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    static {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
    }

}
