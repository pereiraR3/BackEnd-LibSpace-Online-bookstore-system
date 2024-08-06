package com.websystem.libspace.domain.avaliacao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AvaliacaoUpdateDTO(

        @NotNull
        Long id_livro,

        @NotNull
        Long id_user,

        Short nota,

        @Size(max = 1000)
        String comentario

) {

}
