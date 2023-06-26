create table projects
(
    id         varchar(36)  not null primary key,
    name       varchar(100) not null,
    start_date date,
    end_date   date
)
