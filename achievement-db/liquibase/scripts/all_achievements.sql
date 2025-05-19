--liquibase formatted sql

--changeset live4code:create-table-all_achievements
create table if not exists public.all_achievements (
    name      text     not null primary key,
    action    text     not null,
    condition integer  not null
);