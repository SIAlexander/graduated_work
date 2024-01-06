-- liquibase formatted sql

-- changeset asmokvin:01
CREATE table users
(
    id SERIAL PRIMARY KEY,
    email TEXT,
    first_name TEXT,
    last_name TEXT,
    phone TEXT,
    role TEXT,
    image TEXT,
    file_size BIGINT,
    media_type VARCHAR,
    data bytea,
    user_name TEXT,
    password TEXT
);

-- changeset asmokvin:02
CREATE table ads
(
    id SERIAL PRIMARY KEY,
    description TEXT,
    image VARCHAR,
    file_size BIGINT,
    media_type VARCHAR,
    data bytea,
    price INTEGER,
    title TEXT,
    user_id INTEGER REFERENCES users (id)
);

-- changeset asmokvin:03
CREATE table comments
(
    id SERIAL PRIMARY KEY,
    created_at TIMESTAMP,
    text TEXT,
    user_id INTEGER REFERENCES users (id),
    ad_id INTEGER REFERENCES ads (id)
);



