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

create sequence event_seq start 1000;


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists event;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists event_seq;
