DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS user_seq;
DROP TYPE IF EXISTS user_flag;
DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS groups;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq;

CREATE TABLE cities (
  id             INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  city_name      TEXT NOT NULL
);

CREATE TABLE groups (
  id             INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  group_name     TEXT NOT NULL,
  project_name   TEXT NOT NULL
);

CREATE TYPE user_flag AS ENUM ('active', 'deleted', 'superuser');

CREATE SEQUENCE user_seq START 100000;

CREATE TABLE users (
  id        INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
  full_name TEXT NOT NULL,
  email     TEXT NOT NULL,
  flag      user_flag NOT NULL,
  city_id   INTEGER NOT NULL,
  group_id  INTEGER,
  FOREIGN KEY (city_id) REFERENCES cities (id),
  FOREIGN KEY (group_id) REFERENCES groups (id)
);

