GRANT ALL PRIVILEGES ON DATABASE wise_task_profile TO wise_task_profile;
GRANT ALL PRIVILEGES ON DATABASE wise_task_plugin TO wise_task_plugin;
GRANT ALL PRIVILEGES ON DATABASE wise_task_task TO wise_task_task;

\c wise_task_profile;
GRANT USAGE, CREATE ON SCHEMA public TO wise_task_profile;
GRANT ALL ON ALL TABLES IN SCHEMA public TO wise_task_profile;
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO wise_task_profile;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO wise_task_profile;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO wise_task_profile;

\c wise_task_plugin;
GRANT USAGE, CREATE ON SCHEMA public TO wise_task_plugin;
GRANT ALL ON ALL TABLES IN SCHEMA public TO wise_task_plugin;
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO wise_task_plugin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO wise_task_plugin;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO wise_task_plugin;

\c wise_task_task;
GRANT USAGE, CREATE ON SCHEMA public TO wise_task_task;
GRANT ALL ON ALL TABLES IN SCHEMA public TO wise_task_task;
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO wise_task_task;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO wise_task_task;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO wise_task_task;