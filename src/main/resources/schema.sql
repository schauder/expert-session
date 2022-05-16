create table person
(
    id identity not null primary key,
    name varchar(500)
);


create table minion
(
    id varchar(500) not null primary key,
    name varchar(500),
    master bigint references person(id)
);

create table gadget
(
    minion varchar(500) references minion(id),
    description varchar(500),
    primary key (minion, description)
);