-- Create table avaliacao
CREATE TABLE avaliacao (
    id BIGSERIAL PRIMARY KEY,
    id_user BIGINT NOT NULL,
    id_livro BIGINT NOT NULL,
    nota SMALLINT CHECK(nota IN (0, 1, 2, 3, 4, 5)) NOT NULL,
    comentario VARCHAR(1000) NOT NULL,
    data_avaliacao DATE DEFAULT CURRENT_DATE,
    CONSTRAINT fk_user_in_avaliacao FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_livro_in_avaliacao FOREIGN KEY (id_livro) REFERENCES livro (id) ON DELETE CASCADE
);