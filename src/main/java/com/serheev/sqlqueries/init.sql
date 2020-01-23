create DATABASE devcompany_db with OWNER = postgres
ENCODING = 'UTF8'
TABLESPACE = pg_default
CONNECTION LIMIT = -1;

CREATE SEQUENCE hibernate_sequence START 1;

create table company
(
    id int not null,
    name varchar(64) NOT NULL,
    country varchar(32) not null,
    primary key (id),
    unique (name, country)
);

insert into company values (1, 'ITEA', 'Ukraine');
insert into company values (2, 'Bitrix', 'Russia');
insert into company values (3, 'IT Development DE', 'Germany');
insert into company values (4, 'Google', 'USA');

create table developer
(
    id bigint not null,
    name varchar(64) NOT NULL,
    age int,
    sex char,
    salary int,
    onleave boolean,
    primary key (id)
);

insert into developer values (1, 'Bill Gates', 64, 'm', 15000, false);
insert into developer values (2, 'Sergey Brin', 46, 'm', 10000, false);
insert into developer values (3, 'Angela Merkel', 65, 'f', 3000, false);
insert into developer values (4, 'Mark Zuckerberg', 35, 'm', 5000, false);
insert into developer values (5, 'Parisa Tabriz', 38, 'f', 8500, false);
insert into developer values (6, 'Janese Swanson', 61, 'f', 7300, false);

create table company_developer
(
    company_id bigint not null,
    developer_id bigint not null,
    unique (company_id, developer_id),
    foreign key (company_id) references company (id),
    foreign key (developer_id) references developer (id)
);

insert into company_developer values (1, 1);
insert into company_developer values (2, 2);
insert into company_developer values (3, 3);
insert into company_developer values (4, 4);
insert into company_developer values (1, 5);
insert into company_developer values (2, 6);

create table customer
(
    id int not null,
    name varchar(32) NOT NULL,
    phone varchar(32),
    primary key (id)
);

insert into customer values (2, 'Anna Prohorova', '+380125482356');
insert into customer values (3, 'Gennady Dyatlov', '+380971235588');
insert into customer values (4, 'Konstantin Meladze', '+380567894563');
insert into customer values (5, 'Sergey Pivovarov', '+0010040201');
insert into customer values (6, 'Volodimyr Zelenskiy', '+120567894512');
insert into customer values (7, 'Petro Poroshenko', '+23567899542');

create table project
(
    id int not null,
    name varchar(128) NOT NULL,
    start_date date,
    end_date date,
    customer_id bigint,
    cost int,
    primary key (id),
    foreign key (customer_id) references customer (id)
);

insert into project values (1, 'google.com', '2019-11-25', '2020-05-01', 2, 5000000);
insert into project values (2, 'yahoo.com', '2020-01-15', '2022-12-31', 2, 3500000);
insert into project values (3, 'amazon.com', '2018-05-15', '2019-11-01', 3, 1000000);
insert into project values (4, 'ebay.com', '2019-12-05', '2023-06-12', 4, 500000);

create table developer_project
(
    developer_id bigint not null,
    project_id bigint not null,
    unique (developer_id, project_id),
    foreign key (developer_id) references developer (id),
    foreign key (project_id) references project (id)
);

insert into developer_project values (1, 2);
insert into developer_project values (1, 1);
insert into developer_project values (2, 1);
insert into developer_project values (3, 1);
insert into developer_project values (3, 2);
insert into developer_project values (3, 3);
insert into developer_project values (4, 3);
insert into developer_project values (5, 3);
insert into developer_project values (5, 2);
insert into developer_project values (5, 4);
insert into developer_project values (6, 4);
insert into developer_project values (4, 4);

create table skill
(
    id int not null,
    industry varchar(128),
    primary key (id)
);

insert into skill values (1, 'Java');
insert into skill values (2, 'C++');
insert into skill values (3, 'C#');
insert into skill values (4, 'JS');

create table skill_level
(
    level_id int not null,
    level_name varchar(32) not null,
    primary key (level_id)
);

insert into skill_level values (1, 'Junior');
insert into skill_level values (2, 'Middle');
insert into skill_level values (3, 'Senior');

create table developer_skill
(
    developer_id bigint not null,
    skill_id bigint not null,
    skill_level_id bigint not null,
    unique (developer_id, skill_id, skill_level_id),
    unique (developer_id, skill_id),
    foreign key (developer_id) references developer (id),
    foreign key (skill_level_id) references skill_level (level_id),
    foreign key (skill_id) references skill (id)
);

insert into developer_skill values (1, 1, 1);
insert into developer_skill values (1, 2, 3);
insert into developer_skill values (1, 3, 3);
insert into developer_skill values (2, 2, 1);
insert into developer_skill values (2, 3, 3);
insert into developer_skill values (3, 3, 3);
insert into developer_skill values (3, 1, 2);
insert into developer_skill values (4, 1, 2);
insert into developer_skill values (4, 4, 2);
insert into developer_skill values (5, 1, 2);
insert into developer_skill values (5, 2, 3);
insert into developer_skill values (6, 3, 3);
insert into developer_skill values (6, 2, 2);