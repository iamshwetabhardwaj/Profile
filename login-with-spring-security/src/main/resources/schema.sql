drop table if exists app_user;

create table app_user (
    id integer,
    name varchar(255),
    username varchar(255),
    password varchar(255),
    authority varchar(255),
    PRIMARY KEY (ID)
);

create table role(
    id integer,
    name varchar(255),
    PRIMARY KEY (ID)
);

create table app_user_roles(
    users_id integer,
    roles_id integer
);
