\c notification

CREATE TABLE email_log (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255),
                           email_id VARCHAR(255),
                           email_sent BOOLEAN
);