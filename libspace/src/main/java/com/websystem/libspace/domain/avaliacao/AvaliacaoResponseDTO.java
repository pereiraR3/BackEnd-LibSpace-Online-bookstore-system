package com.websystem.libspace.domain.avaliacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AvaliacaoResponseDTO(

        Long id,

        Long id_user,

        Short nota,

        String comentario,

        LocalDate dataAvalicao

) {
    public AvaliacaoResponseDTO(Avaliacao avaliacao){

        this(
                avaliacao.getId(),
                avaliacao.getUser().getId(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getDataAvaliacao()
        );

    }


}
