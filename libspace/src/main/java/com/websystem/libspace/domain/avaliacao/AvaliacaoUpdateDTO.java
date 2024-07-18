package com.websystem.libspace.domain.avaliacao;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AvaliacaoUpdateDTO(

        @NotNull
        Long id,

        @NotNull
        Long id_user,

        Short nota,

        @Size(max = 1000)
        String comentario

) {

}
