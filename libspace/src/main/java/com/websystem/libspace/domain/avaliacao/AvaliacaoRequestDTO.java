package com.websystem.libspace.domain.avaliacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AvaliacaoRequestDTO(

        @NotNull
        Long id_livro,

        @NotNull
        Long id_user,

        @NotNull
        Short nota,

        @NotBlank
        @Size(max = 1000)
        String comentario

) {
}
