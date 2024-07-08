-- Create table livro_ebook
CREATE TABLE livro_ebook (
    id_livro BIGINT PRIMARY KEY,
    tamanho_arquivo SMALLINT NOT NULL,
    formato_arquivo VARCHAR(40) NOT NULL,
    CONSTRAINT fk_livro_in_livroebook FOREIGN KEY (id_livro) REFERENCES livro (id) ON DELETE CASCADE
);