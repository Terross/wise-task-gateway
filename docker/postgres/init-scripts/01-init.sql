CREATE DATABASE wise_task_profile;
CREATE DATABASE wise_task_plugin;
CREATE DATABASE wise_task_task;

DO $$
    BEGIN
        IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'wise_task_profile') THEN
            CREATE USER wise_task_profile WITH PASSWORD 'wise_task_profile';
        END IF;

        IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'wise_task_plugin') THEN
            CREATE USER wise_task_plugin WITH PASSWORD 'wise_task_plugin';
        END IF;

        IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'wise_task_task') THEN
            CREATE USER wise_task_task WITH PASSWORD 'wise_task_task';
        END IF;
    END $$;

GRANT ALL PRIVILEGES ON DATABASE wise_task_profile TO wise_task_profile;
GRANT ALL PRIVILEGES ON DATABASE wise_task_plugin TO wise_task_plugin;
GRANT ALL PRIVILEGES ON DATABASE wise_task_task TO wise_task_task;


\c wise_task_profile;
GRANT USAGE, CREATE ON SCHEMA public TO wise_task_profile;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO wise_task_profile;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO wise_task_profile;

\c wise_task_plugin;
GRANT USAGE, CREATE ON SCHEMA public TO wise_task_plugin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO wise_task_plugin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO wise_task_plugin;

\c wise_task_task;
GRANT USAGE, CREATE ON SCHEMA public TO wise_task_task;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO wise_task_task;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO wise_task_task;