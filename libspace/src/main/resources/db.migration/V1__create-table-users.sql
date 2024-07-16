-- Create table users
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    cpf CHAR(11) UNIQUE NOT NULL,
    username VARCHAR(60) UNIQUE NOT NULL,
    password VARCHAR(80) NOT NULL,
    email VARCHAR(120) UNIQUE NOT NULL,
    url_foto VARCHAR(1000),
    url_website VARCHAR(1000),
    bio VARCHAR(2000) NOT NULL,
    role VARCHAR(20) NOT NULL
);