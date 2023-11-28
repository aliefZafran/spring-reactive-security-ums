CREATE TABLE IF NOT EXISTS my_user (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255),
    password VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    role VARCHAR(255),
    about VARCHAR(255));