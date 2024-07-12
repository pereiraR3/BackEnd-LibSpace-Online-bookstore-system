-- Create table livro_possui_genero
CREATE TABLE livro_possui_genero (
    id_livro BIGINT NOT NULL,
    id_genero BIGINT NOT NULL,
    CONSTRAINT uq_idlivro_e_idgenero UNIQUE (id_livro, id_genero),
    CONSTRAINT fk_livro_in_livro_possui_categoria FOREIGN KEY (id_livro) REFERENCES livro (id) ON DELETE CASCADE,
    CONSTRAINT fk_categoria_in_livro_possui_categoria FOREIGN KEY (id_genero) REFERENCES genero (id) ON DELETE CASCADE
);