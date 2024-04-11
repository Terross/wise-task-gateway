CREATE SCHEMA IF NOT EXISTS wise_task_task;

CREATE TABLE wise_task_task.task
(
    id          UUID    NOT NULL PRIMARY KEY,
    name        VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    category    VARCHAR NOT NULL,
    task_type   VARCHAR NOT NULL,
    author_id   UUID    NOT NULL,
    is_public   BOOLEAN NOT NULL DEFAULT false
);

CREATE TABLE wise_task_task.task_implementation
(
    id        UUID NOT NULL PRIMARY KEY,
    plugin_id UUID NOT NULL,

    CONSTRAINT fk_task_id FOREIGN KEY (id) REFERENCES wise_task_task.task (id)
);

CREATE TABLE wise_task_task.task_graph
(
    id                UUID    NOT NULL PRIMARY KEY,
    is_hidden_mistake BOOLEAN NOT NULL DEFAULT false,
    graph_id          UUID,
    condition         jsonb, -- [{"pluginId":"123","value":"123","mistakeText":"123",?"sign":"MORE"}]
    rule             jsonb, -- {"color":false,"edit":true}

    CONSTRAINT fk_task_id FOREIGN KEY (id) REFERENCES wise_task_task.task (id)
);

CREATE TABLE wise_task_task.task_text
(
    id        UUID NOT NULL PRIMARY KEY,
    plugin_id UUID NOT NULL,

    CONSTRAINT fk_task_id FOREIGN KEY (id) REFERENCES wise_task_task.task (id)
);

CREATE TABLE wise_task_task.catalog
(
    id          UUID    NOT NULL PRIMARY KEY,
    name        VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    author_id   UUID    NOT NULL,
    student_ids UUID[]
);

CREATE TABLE wise_task_task.task_catalog
(
    catalog_id UUID NOT NULL,
    task_id    UUID NOT NULL,

    CONSTRAINT pk_task_catalog PRIMARY KEY (catalog_id, task_id),

    CONSTRAINT fk_catalog_id FOREIGN KEY (catalog_id) REFERENCES wise_task_task.catalog (id),
    CONSTRAINT fk_task_id FOREIGN KEY (task_id) REFERENCES wise_task_task.task (id)
);

CREATE TABLE wise_task_task.solution
(
    id         UUID    NOT NULL PRIMARY KEY,
    task_id    UUID    NOT NULL,
    author_id  UUID    NOT NULL,
    is_correct BOOLEAN NOT NULL,

    CONSTRAINT fk_task_id FOREIGN KEY (task_id) REFERENCES wise_task_task.task (id)
);

CREATE TABLE wise_task_task.solution_graph
(
    id            UUID  NOT NULL PRIMARY KEY,
    graph_id      UUID  NOT NULL,
    result jsonb NOT NULL, -- '[{"pluginId:"123", "isCorrect":true, "value":"123", ?"trueValue":"124"}]'

    CONSTRAINT fk_solution_id FOREIGN KEY (id) REFERENCES wise_task_task.solution (id)
);

CREATE TABLE wise_task_task.solution_implementation
(
    id                    UUID    NOT NULL PRIMARY KEY,
    code                  VARCHAR NOT NULL,
    implementation_result jsonb   NOT NULL, -- '[{"graphId":"123","originalTimeResult":15,"timeResult":10,"originalResult":"5", "result":"5"}]'

    CONSTRAINT fk_solution_id FOREIGN KEY (id) REFERENCES wise_task_task.solution (id)
);