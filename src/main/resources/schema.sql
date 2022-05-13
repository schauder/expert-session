create table minion
(
    id identity not null primary key,
    name varchar(500)
);

create table gadget
(
    minion bigint references minion(id),
    description varchar(500),
    primary key (minion, description)
);
