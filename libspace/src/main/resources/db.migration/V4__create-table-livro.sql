-- Create table livro
CREATE TABLE livro (
    id BIGSERIAL PRIMARY KEY,
    id_editora BIGINT DEFAULT NULL,
    preco_unitario MONEY NOT 0.00,
    preco_acumulado MONEY DEFAULT preco_unitario,
    titulo VARCHAR(120) UNIQUE NOT NULL,
    quantidade SMALLINT NOT NULL,
    autor_nome VARCHAR(160) NOT NULL,
    ano_publicacao SMALLINT NOT NULL,
    capa_url TEXT NOT NULL,
    CONSTRAINT fk_editora_in_livro FOREIGN KEY (id_editora) REFERENCES editora (id) ON DELETE SET NULL
);