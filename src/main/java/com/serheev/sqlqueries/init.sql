CREATE DATABASE devcompany WITH OWNER = postgres
ENCODING = 'UTF8'
TABLESPACE = pg_default
CONNECTION LIMIT = -1;

CREATE TABLE company
(
    id int NOT NULL,
    name varchar(64) NOT NULL,
    country varchar(32) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name, country)
);

INSERT INTO company VALUES (1, 'ITEA', 'Ukraine');
INSERT INTO company VALUES (2, 'Bitrix', 'Russia');
INSERT INTO company VALUES (3, 'IT Development DE', 'Germany');
INSERT INTO company VALUES (4, 'Google', 'USA');

CREATE TABLE developer
(
    id bigint NOT NULL,
    name varchar(64) NOT NULL,
    age int,
    sex char,
    salary int,
    PRIMARY KEY (id)
);

INSERT INTO developer VALUES (1, 'Bill Gates', 64, 'm', 15000);
INSERT INTO developer VALUES (2, 'Sergey Brin', 46, 'm', 10000);
INSERT INTO developer VALUES (3, 'Angela Merkel', 65, 'f', 3000);
INSERT INTO developer VALUES (4, 'Mark Zuckerberg', 35, 'm', 5000);
INSERT INTO developer VALUES (5, 'Parisa Tabriz', 38, 'f', 8500);
INSERT INTO developer VALUES (6, 'Janese Swanson', 61, 'f', 7300);

CREATE TABLE company_developer
(
    company_id bigint NOT NULL,
    developer_id bigint NOT NULL,
    UNIQUE (company_id, developer_id),
    FOREIGN KEY (company_id) REFERENCES company (id),
    FOREIGN KEY (developer_id) REFERENCES developer (id)
);

INSERT INTO company_developer VALUES (1, 1);
INSERT INTO company_developer VALUES (2, 2);
INSERT INTO company_developer VALUES (3, 3);
INSERT INTO company_developer VALUES (4, 4);
INSERT INTO company_developer VALUES (1, 5);
INSERT INTO company_developer VALUES (2, 6);

CREATE TABLE customer
(
    id int NOT NULL,
    name varchar(32) NOT NULL,
    phone varchar(32),
    PRIMARY KEY (id)
);

INSERT INTO customer VALUES (2, 'Anna Prohorova', '+380125482356');
INSERT INTO customer VALUES (3, 'Gennady Dyatlov', '+380971235588');
INSERT INTO customer VALUES (4, 'Konstantin Meladze', '+380567894563');
INSERT INTO customer VALUES (5, 'Sergey Pivovarov', '+0010040201');
INSERT INTO customer VALUES (6, 'Volodimyr Zelenskiy', '+120567894512');
INSERT INTO customer VALUES (7, 'Petro Poroshenko', '+23567899542');

CREATE TABLE project
(
    id int NOT NULL,
    name varchar(128) NOT NULL,
    start_date date,
    end_date date,
    customer_id bigint,
    cost int,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

INSERT INTO project VALUES (1, 'google.com', '2019-11-25', '2020-05-01', 2, 5000000);
INSERT INTO project VALUES (2, 'yahoo.com', '2020-01-15', '2022-12-31', 2, 3500000);
INSERT INTO project VALUES (3, 'amazon.com', '2018-05-15', '2019-11-01', 3, 1000000);
INSERT INTO project VALUES (4, 'ebay.com', '2019-12-05', '2023-06-12', 4, 500000);

CREATE TABLE developer_project
(
    developer_id bigint NOT NULL,
    project_id bigint NOT NULL,
    UNIQUE (developer_id, project_id),
    FOREIGN KEY (developer_id) REFERENCES developer (id),
    FOREIGN KEY (project_id) REFERENCES project (id)
);

INSERT INTO developer_project VALUES (1, 2);
INSERT INTO developer_project VALUES (1, 1);
INSERT INTO developer_project VALUES (2, 1);
INSERT INTO developer_project VALUES (3, 1);
INSERT INTO developer_project VALUES (3, 2);
INSERT INTO developer_project VALUES (3, 3);
INSERT INTO developer_project VALUES (4, 3);
INSERT INTO developer_project VALUES (5, 3);
INSERT INTO developer_project VALUES (5, 2);
INSERT INTO developer_project VALUES (5, 4);
INSERT INTO developer_project VALUES (6, 4);
INSERT INTO developer_project VALUES (4, 4);

CREATE TABLE skill
(
    id int NOT NULL,
    industry varchar(128),
    PRIMARY KEY (id)
);

INSERT INTO skill VALUES (1, 'Java');
INSERT INTO skill VALUES (2, 'C++');
INSERT INTO skill VALUES (3, 'C#');
INSERT INTO skill VALUES (4, 'JS');

CREATE TABLE skill_level
(
    level_id int NOT NULL,
    level_name varchar(32) NOT NULL,
    PRIMARY KEY (level_id)
);

INSERT INTO skill_level VALUES (1, 'Junior');
INSERT INTO skill_level VALUES (2, 'Middle');
INSERT INTO skill_level VALUES (3, 'Senior');

CREATE TABLE developer_skill
(
    developer_id bigint NOT NULL,
    skill_id bigint NOT NULL,
    skill_level_id bigint NOT NULL,
    UNIQUE (developer_id, skill_id, skill_level_id),
    UNIQUE (developer_id, skill_id),
    FOREIGN KEY (developer_id) REFERENCES developer (id),
    FOREIGN KEY (skill_level_id) REFERENCES skill_level (level_id),
    FOREIGN KEY (skill_id) REFERENCES skill (id)
);

INSERT INTO developer_skill VALUES (1, 1, 1);
INSERT INTO developer_skill VALUES (1, 2, 3);
INSERT INTO developer_skill VALUES (1, 3, 3);
INSERT INTO developer_skill VALUES (2, 2, 1);
INSERT INTO developer_skill VALUES (2, 3, 3);
INSERT INTO developer_skill VALUES (3, 3, 3);
INSERT INTO developer_skill VALUES (3, 1, 2);
INSERT INTO developer_skill VALUES (4, 1, 2);
INSERT INTO developer_skill VALUES (4, 4, 2);
INSERT INTO developer_skill VALUES (5, 1, 2);
INSERT INTO developer_skill VALUES (5, 2, 3);
INSERT INTO developer_skill VALUES (6, 3, 3);
INSERT INTO developer_skill VALUES (6, 2, 2);