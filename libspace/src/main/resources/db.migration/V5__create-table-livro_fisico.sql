-- Create table livro_fisico
CREATE TABLE livro_fisico (
    id_livro BIGSERIAL PRIMARY KEY,
    numero_de_paginas SMALLINT NOT NULL,
    peso SMALLINT NOT NULL,
    tipo_capa VARCHAR(60) NOT NULL,
    dimensao_altura SMALLINT NOT NULL,
    dimensao_largura SMALLINT NOT NULL,
    dimensao_profundidade SMALLINT NOT NULL,
    CONSTRAINT fk_livro_in_livrofisico FOREIGN KEY (id_livro) REFERENCES livro (id) ON DELETE CASCADE
);