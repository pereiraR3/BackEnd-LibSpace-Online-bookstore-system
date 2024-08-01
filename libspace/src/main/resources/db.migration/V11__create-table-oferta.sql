-- Create table oferta
CREATE TABLE oferta (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    id_livro BIGINT NOT NULL,
    id_editora BIGINT DEFAULT NULL,
    preco_revenda MONEY NOT NULL,
    desconto DECIMAL(5, 2) DEFAULT 0,
    CONSTRAINT uq_livro_e_id UNIQUE (id, id_livro),
    CONSTRAINT fk_livro_in_oferta FOREIGN KEY (id_livro) REFERENCES livro (id) ON DELETE CASCADE,
    CONSTRAINT fk_editora_in_oferta FOREIGN KEY (id_editora) REFERENCES editora (id) ON DELETE SET NULL
);