-- Create table impostos
CREATE TABLE impostos (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    nome VARCHAR(100) NOT NULL,
    codigo INT NOT NULL,
    percentual FLOAT NOT NULL,
);

