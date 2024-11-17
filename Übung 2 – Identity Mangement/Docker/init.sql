create database LVAServiceDB;
use LVAServiceDB;

create or replace table lva_leader
(
    id        bigint auto_increment
        primary key,
    fist_name varchar(255) not null,
    last_name varchar(255) not null
);

create or replace table lvas
(
    id            bigint auto_increment
        primary key,
    name          varchar(255) not null,
    lva_leader_id bigint       not null,
    constraint FKqgllj4j4j49is8sufm320naxf
        foreign key (lva_leader_id) references lva_leader (id)
);

create or replace table student
(
    id        bigint auto_increment
        primary key,
    fist_name varchar(255) not null,
    last_name varchar(255) not null
);

create or replace table exercises
(
    id            bigint auto_increment
        primary key,
    feedback      varchar(255) null,
    submission    varchar(255) null,
    lva_id        bigint       null,
    lva_leader_id bigint       null,
    student_id    bigint       null,
    constraint FK6g0wx92dyo1nxlcoylauxry7s
        foreign key (student_id) references student (id),
    constraint FKa0xoj5fjtci5ghrit60q3c5f7
        foreign key (lva_leader_id) references lva_leader (id),
    constraint FKac1p18omg3cxhhoj0l271kat1
        foreign key (lva_id) references lvas (id)
);

create or replace table lvas_students
(
    lva_id      bigint not null,
    students_id bigint not null,
    constraint FKfmq531a31869954sh60sk8iod
        foreign key (students_id) references student (id),
    constraint FKnnant7jmpj37aj7h5oxwps3ip
        foreign key (lva_id) references lvas (id)
);




commit;