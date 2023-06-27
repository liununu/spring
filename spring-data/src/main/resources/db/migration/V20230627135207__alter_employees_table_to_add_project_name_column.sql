alter table employees
    add column project_name varchar(100) null;

update employees
set project_name = (select projects.name from projects where projects.id = employees.id)
where employees.project_id is not null;

alter table employees
    drop column project_id;
