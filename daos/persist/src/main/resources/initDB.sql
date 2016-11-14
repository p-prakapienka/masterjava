DROP TABLE IF EXISTS users;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  full_name  VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  flag       INTEGER NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);