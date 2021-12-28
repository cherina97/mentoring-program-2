create table users
(
    id    serial       not null primary key,
    name  varchar(255) not null,
    email varchar(255) not null unique
);

create table events
(
    id           serial       not null primary key,
    title        varchar(255) not null,
    date         date         not null,
    ticket_price decimal      not null
);

create table tickets
(
    id       serial       not null primary key,
    event_id integer references events (id),
    user_id  integer references users (id),
    category varchar(255) not null,
    place    integer      not null
);

create table users_accounts
(
    id      serial not null primary key,
    user_id integer references users (id) unique,
    money   decimal
);