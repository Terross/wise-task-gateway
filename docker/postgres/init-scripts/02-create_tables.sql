\c wise_task_profile
create table profile (
                         id uuid not null,
                         email varchar(255),
                         first_name varchar(255),
                         last_name varchar(255),
                         patronymic varchar(255),
                         profile_password varchar(255),
                         profile_role varchar(255),
                         student_group varchar(255),
                         primary key (id)
);

create table password_recovery (
                                   recovery_token uuid not null,
                                   email varchar(255),
                                   expires_at timestamp(6),
                                   primary key (recovery_token)
);

\c wise_task_task
create table catalog (
                         author_id uuid,
                         id uuid not null,
                         catalog varchar(255),
                         name varchar(255),
                         primary key (id)
);

create table catalog_student_ids (
                                     catalog_id uuid not null,
                                     student_ids uuid
);
create table solution (
                          is_correct boolean,
                          author_id uuid,
                          id uuid not null,
                          task_id uuid,
                          primary key (id)
);
create table solution_graph (
                                graph_id uuid,
                                id uuid not null,
                                result jsonb,
                                primary key (id)
);
create table solution_implementation (
                                         id uuid not null,
                                         code varchar(255),
                                         implementation_result jsonb,
                                         primary key (id)
);
create table task (
                      is_public boolean,
                      author_id uuid,
                      id uuid not null,
                      category varchar(255),
                      description varchar(255),
                      name varchar(255),
                      task_type varchar(255) check (task_type in ('IMPLEMENTATION','GRAPH')),
                      primary key (id)
);
create table task_catalog (
                              catalog_id uuid not null,
                              task_id uuid
);
create table task_graph (
                            is_hidden_mistake boolean,
                            graph_id uuid,
                            id uuid not null,
                            condition jsonb,
                            rule jsonb,
                            primary key (id)
);
create table task_implementation (
                                     id uuid not null,
                                     plugin_id uuid,
                                     primary key (id)
);
alter table if exists catalog_student_ids
    add constraint FK3vp0aj24cls00qfbw43x0175d
    foreign key (catalog_id)
    references catalog;

alter table if exists solution_graph
    add constraint FK9j8f7ya0we8l9d1qgxhb2sxwr
    foreign key (id)
    references solution;

alter table if exists solution_implementation
    add constraint FKpouwsj45b4ua1eobf6v3qghg7
    foreign key (id)
    references solution;

alter table if exists task_catalog
    add constraint FK2x5vv3las4inaro4ow57w4ahm
    foreign key (catalog_id)
    references catalog;

alter table if exists task_graph
    add constraint FK4irpkxn11fha2kvb3bxivxtyg
    foreign key (id)
    references task;

alter table if exists task_implementation
    add constraint FKpx6vuo2o6t97sux59dbbhdvca
    foreign key (id)
    references task;

\c wise_task_plugin
create table plugin (
                        id uuid not null,
                        author_id uuid,
                        bean_name varchar(255),
                        category varchar(255),
                        description varchar(255),
                        graph_type varchar(255),
                        is_internal boolean,
                        is_valid boolean,
                        jar_file bytea,
                        jar_name varchar(255),
                        name varchar(255),
                        plugin_type varchar(255),
                        primary key (id)
);
