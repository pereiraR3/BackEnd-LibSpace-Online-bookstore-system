-- Create table impostos_pertence_a_livro
CREATE TABLE impostos_pertence_a_livro (
    id_livro BIGSERIAL NOT NULL,
    id_impostos BIGSERIAL NOT NULL,
    CONSTRAINT fk_id_livro_in_impostos_impostos_pertence_a_livro FOREIGN id_livro REFERENCES livro (id),
    CONSTRAINT fk_id_impostos_in_impostos_impostos_pertence_a_livro FOREIGN id_impostos REFERENCES impostos (id)
    PRIMARY KEY (id_livro, id_impostos)
);

