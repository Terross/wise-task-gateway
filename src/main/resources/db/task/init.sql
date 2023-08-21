CREATE USER wise_task_task WITH PASSWORD 'wise_task_task';
CREATE DATABASE wise_task_task WITH OWNER = wise_task_task;
CREATE SCHEMA wise_task_task;

-- docker run --name profile -e POSTGRES_PASSWORD=wise_task_task -e POSTGRES_DB=wise_task_task -e POSTGRES_USER=wise_task_task -p 5434:5432 -d postgres