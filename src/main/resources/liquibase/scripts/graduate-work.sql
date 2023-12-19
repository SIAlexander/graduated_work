-- liquibase formatted sql

-- changeset asmokvin:01
CREATE table users
(
    id INTEGER PRIMARY KEY,
    email TEXT,
    first_name TEXT,
    last_name TEXT,
    phone TEXT,
    role TEXT,
    image TEXT,
    user_name TEXT,
    password TEXT
);

-- changeset asmokvin:02
CREATE table comments
(
    id INTEGER PRIMARY KEY,
    created_at INTEGER,
    text TEXT,
    user_id INTEGER REFERENCES users (id)
);

-- changeset asmokvin:03
CREATE table ads
(
    id INTEGER PRIMARY KEY,
    description TEXT,
    image TEXT,
    price INTEGER,
    title TEXT,
    user_id INTEGER REFERENCES users (id)
);
