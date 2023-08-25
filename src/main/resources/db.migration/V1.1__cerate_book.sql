create table book(
    id serial not null,
    description varchar(255) default null,
    title varchar(255) default null,
    primary key (id)
);