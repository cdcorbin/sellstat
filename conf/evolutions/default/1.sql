# --- First database schema

# --- !Ups

create table event (
  id                        bigint not null,
  uid                       varchar(255),
  longitude                 float8,
  latitude                  float8,
  eventat                   varchar(255),
  constraint pk_event primary key (id))
;

create table modeled_schedule (
  id                        bigint not null,
  uid                       varchar(255),
  json                      varchar(4096),
  constraint pk_modeled_schedule primary key (id))
;

create sequence event_seq start 1000;
create sequence modeled_schedule_seq start 1000;


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists event;
drop table if exists modeled_schedule;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists event_seq;
drop sequence if exists modeled_schedule_seq;
