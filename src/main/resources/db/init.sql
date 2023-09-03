-- plugin
CREATE USER wise_task_plugin WITH PASSWORD 'wise_task_plugin';
CREATE DATABASE wise_task_plugin WITH OWNER = wise_task_plugin;
CREATE SCHEMA wise_task_plugin;
-- docker run --name plugin -e POSTGRES_PASSWORD=wise_task_plugin -e POSTGRES_DB=wise_task_plugin -e POSTGRES_USER=wise_task_plugin -p 5432:5432 -d postgres


-- profile
CREATE USER wise_task_profile WITH PASSWORD 'wise_task_profile';
CREATE DATABASE wise_task_profile WITH OWNER = wise_task_profile;
CREATE SCHEMA wise_task_profile;
-- docker run --name profile -e POSTGRES_PASSWORD=wise_task_profile -e POSTGRES_DB=wise_task_profile -e POSTGRES_USER=wise_task_profile -p 5433:5432 -d postgres


-- task
CREATE USER wise_task_task WITH PASSWORD 'wise_task_task';
CREATE DATABASE wise_task_task WITH OWNER = wise_task_task;
CREATE SCHEMA wise_task_task;
-- docker run --name profile -e POSTGRES_PASSWORD=wise_task_task -e POSTGRES_DB=wise_task_task -e POSTGRES_USER=wise_task_task -p 5434:5432 -d postgres