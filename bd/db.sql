create table j_markAuto (
    id serial primary key,
    name varchar(2000)
);

create table j_modelAuto (
    id serial primary key,
    name varchar(2000)
);

create table book (
    id serial primary key,
    name varchar(2000)
);

create table author (
    id serial primary key,
    name varchar(2000)
);
select * from author;

drop table j_markAuto;
drop table j_modelAuto;

drop table j_markAuto_j_modelAuto;