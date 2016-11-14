DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (full_name, email, flag)
VALUES ('Test User1', 'user1@gmail.com', 2);
INSERT INTO users (full_name, email, flag)
VALUES ('Test User2', 'user2@gmail.com', 0);
INSERT INTO users (full_name, email, flag)
VALUES ('Deleted User', 'deleted@gmail.com', 1);