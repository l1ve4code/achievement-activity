--liquibase formatted sql

--changeset live4code:create-table-statistics
create table if not exists public.statistics (
    user_id bigint not null,
    action  text   not null,
    amount  bigint not null
);