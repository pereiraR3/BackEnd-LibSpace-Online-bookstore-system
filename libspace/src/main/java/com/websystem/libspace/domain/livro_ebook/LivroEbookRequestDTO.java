package com.websystem.libspace.domain.livro_ebook;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LivroEbookRequestDTO(


        // - Relativo a classe pai (Livro)

        @NotNull
        Long id,

        // - Relativo a classe filha (LivroEbook)

        @NotNull
        Short tamanho_arquivo,

        @NotBlank
        @Size(max = 40)
        String formato_arquivo

) {
}
