CREATE SCHEMA IF NOT EXISTS wise_task_profile;

CREATE TABLE wise_task_profile.password_recovery
(
    recovery_token UUID NOT NULL PRIMARY KEY,
    email VARCHAR NOT NULL,
    expires_at TIMESTAMP NOT NULL
);