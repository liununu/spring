create table employees
(
    id         varchar(36)  not null primary key,
    name       varchar(100) not null,
    email      varchar(100) not null,
    project_id varchar(36),
    active     boolean      not null default true
)
