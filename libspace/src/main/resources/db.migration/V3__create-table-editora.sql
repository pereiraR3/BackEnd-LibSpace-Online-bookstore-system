-- Create table editora
CREATE TABLE editora (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    cnpj BIGINT UNIQUE NOT NULL,
    cep BIGINT NOT NULL,
    telefone CHAR(11) NOT NULL,
    email VARCHAR(120) UNIQUE NOT NULL,
    url_website TEXT
);