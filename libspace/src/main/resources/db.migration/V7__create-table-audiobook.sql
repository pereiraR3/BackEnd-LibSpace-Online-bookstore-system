-- Create table livro_audiobook
CREATE TABLE livro_audiobook (
    id_livro BIGSERIAL PRIMARY KEY,
    tamanho_arquivo SMALLINT NOT NULL,
    formato_arquivo VARCHAR(40) NOT NULL,
    narrador VARCHAR(120) NOT NULL,
    url_download TEXT NOT NULL,
    CONSTRAINT fk_livro_in_livroaudiobook FOREIGN KEY (id_livro) REFERENCES livro (id) ON DELETE CASCADE
);