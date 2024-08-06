package com.websystem.libspace.domain.avaliacao;

import java.time.LocalDate;

public record AvaliacaoResponseDTO(

        Long id_livro,

        Long id_user,

        Short nota,

        String comentario,

        LocalDate dataAvalicao

) {
    public AvaliacaoResponseDTO(Avaliacao avaliacao){

        this(
                avaliacao.getLivro().getId(),
                avaliacao.getUser().getId(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getDataAvaliacao()
        );

    }

}
