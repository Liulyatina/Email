CREATE SCHEMA Mail_app
    AUTHORIZATION mail_app;

CREATE TABLE mail_app.emails
(
    id        SERIAL PRIMARY KEY,
    dt_create TIMESTAMP,
    status    VARCHAR(255),
    recipient VARCHAR(255),
    subject   VARCHAR(255),
    text      TEXT
);

CREATE TABLE mail_app.users
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(255),
    password VARCHAR(255),
    birthday DATE,
    fullname VARCHAR(255)
);


ALTER TABLE IF EXISTS Mail_app.Emails
    OWNER to mail_app;
