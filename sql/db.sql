CREATE SCHEMA Mail_app
    AUTHORIZATION mail_app;

CREATE TABLE Mail_app.Emails
(
    id SERIAL PRIMARY KEY,
    recipient VARCHAR(255),
    subject VARCHAR(255),
    text TEXT
);

ALTER TABLE IF EXISTS Mail_app.Emails
    OWNER to mail_app;
