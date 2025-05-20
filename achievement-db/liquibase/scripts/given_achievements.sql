--liquibase formatted sql

--changeset live4code:create-table-given_achievements
create table if not exists public.given_achievements (
    user_id          bigint not null,
    achievement_name text  not null,
    constraint pk_given_achievements primary key (user_id, achievement_name)
);