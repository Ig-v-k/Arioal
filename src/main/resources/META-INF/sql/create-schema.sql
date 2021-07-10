create table users
(
    id            serial,
    name          varchar(50)        not null,
    username      varchar(50) unique not null,
    user_password varchar(255)       not null,
    user_group    varchar(25)
);