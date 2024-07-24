CREATE TABLE IF NOT EXISTS users
(
    id        uuid DEFAULT gen_random_uuid() NOT NULL
    PRIMARY KEY,
    firstname VARCHAR(255),
    lastname  VARCHAR(255),
    email     VARCHAR(255));