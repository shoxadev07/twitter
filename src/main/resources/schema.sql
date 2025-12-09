CREATE TABLE IF NOT EXISTS students
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(100),
    surname VARCHAR(100),
    age     INT                 NOT NULL,
    email   VARCHAR(100) UNIQUE NOT NULL,
    phone   VARCHAR(15) UNIQUE  NOT NULL,
    delete_at TIMESTAMP DEFAULT NULL
);