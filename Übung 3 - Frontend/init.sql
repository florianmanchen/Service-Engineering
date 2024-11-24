create database LVAServiceDB;
use LVAServiceDB;

create or replace table products
(
    id          bigint auto_increment
        primary key,
    description varchar(255) null,
    img         varchar(255) null,
    price       int          null,
    title       varchar(255) null
);

create or replace table orders
(
    id    bigint auto_increment
        primary key,
    price int null
);

create or replace table orders_products
(
    order_id    bigint not null,
    products_id bigint not null,
    constraint FKe4y1sseio787e4o5hrml7omt5
        foreign key (order_id) references orders (id),
    constraint FKqgxvu9mvqx0bv2ew776laoqvv
        foreign key (products_id) references products (id)
);

commit;