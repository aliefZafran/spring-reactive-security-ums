CREATE TABLE IF NOT EXISTS my_user (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    role VARCHAR(255),
    about VARCHAR(255),
    verification_code VARCHAR(255),
    reset_token VARCHAR(255),
    disabled BOOLEAN
);
