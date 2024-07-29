-- Create table person if not exist
CREATE TABLE IF NOT EXISTS person
(
    id             SERIAL PRIMARY KEY,
    identification VARCHAR(255)             NOT NULL,
    name           VARCHAR(255)             NOT NULL,
    gender         VARCHAR(50),
    age            INT,
    address        VARCHAR(255),
    cellphone      VARCHAR(50)
);


-- Create table customer if not exist
CREATE TABLE IF NOT EXISTS customer
(
    id         SERIAL PRIMARY KEY,
    password   VARCHAR(255)             NOT NULL,
    client_id  VARCHAR(255)             NOT NULL,
    state      BOOLEAN                           DEFAULT FALSE
);

CREATE INDEX IF NOT EXISTS idx_client_id ON customer(client_id);
CREATE INDEX IF NOT EXISTS idx_identification ON person(identification);

