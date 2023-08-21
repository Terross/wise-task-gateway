CREATE SCHEMA IF NOT EXISTS wise_task_task;

CREATE TABLE wise_task_task.taskEntity
(
    id          UUID    NOT NULL PRIMARY KEY,
    name        VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    category    VARCHAR NOT NULL,
    author_id   UUID    NOT NULL,
    graph_id    UUID
);

CREATE TABLE wise_task_task.condition
(
    id        UUID NOT NULL PRIMARY KEY,
    task_id   UUID NOT NULL,
    plugin_id UUID NOT NULL,

    CONSTRAINT fk_task_id FOREIGN KEY (task_id) REFERENCES wise_task_task.taskEntity (id)
);

CREATE TABLE wise_task_task.answer
(
    id          UUID    NOT NULL PRIMARY KEY,
    plugin_type VARCHAR NOT NULL,
    value       VARCHAR NOT NULL,

    CONSTRAINT fk_condition_id FOREIGN KEY (id) REFERENCES wise_task_task.condition (id)
);

CREATE TABLE wise_task_task.solution
(
    id         UUID    NOT NULL PRIMARY KEY,
    task_id    UUID    NOT NULL,
    author_id  UUID    NOT NULL,
    is_correct BOOLEAN NOT NULL,
    graph_id   UUID    NOT NULL,

    CONSTRAINT fk_task_id FOREIGN KEY (task_id) REFERENCES wise_task_task.taskEntity (id)
);

CREATE TABLE wise_task_task.plugin_result
(
    id                 UUID    NOT NULL PRIMARY KEY,
    plugin_id          UUID    NOT NULL,
    is_correct         BOOLEAN NOT NULL,
    solution_id        UUID    NOT NULL,
    answer_id          UUID    NOT NULL,
    excepted_answer_id UUID    NOT NULL,

    CONSTRAINT fk_solution_id FOREIGN KEY (solution_id) REFERENCES wise_task_task.solution (id),
    CONSTRAINT fk_answer_id FOREIGN KEY (answer_id) REFERENCES wise_task_task.answer (id),
    CONSTRAINT fk_excepted_answer_id FOREIGN KEY (excepted_answer_id) REFERENCES wise_task_task.answer (id)
);