drop table if exists person CASCADE;
drop table if exists personal_accomplishment CASCADE; 
drop table if exists project_team CASCADE;

create table project_team (
code varchar(64) NOT NULL UNIQUE,
description varchar(1024) NOT NULL,
project_name varchar(256) NOT NULL,
status varchar(64) NOT NULL
);

create table person (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
level varchar(64) NOT NULL,
name varchar(255) NOT NULL,
project_role varchar(64) NOT NULL,
skills varchar(255) NOT NULL,
start_date date NOT NULL,
project_team_code varchar(64) NOT NULL
);

ALTER TABLE person ADD FOREIGN KEY (project_team_code) REFERENCES project_team(code);

create table personal_accomplishment (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
description varchar(255),
time_costs integer,
person_id BIGINT not null,
project_team_code varchar(64) not null
);

ALTER TABLE personal_accomplishment ADD FOREIGN KEY (person_id) REFERENCES person(id);
ALTER TABLE personal_accomplishment ADD FOREIGN KEY (project_team_code) REFERENCES project_team(code);
ALTER TABLE personal_accomplishment ADD UNIQUE MyConstraint (person_id, project_team_code);
