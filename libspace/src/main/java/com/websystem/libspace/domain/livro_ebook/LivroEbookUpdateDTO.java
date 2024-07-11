package com.websystem.libspace.domain.livro_ebook;

import jakarta.validation.constraints.NotNull;

public record LivroEbookUpdateDTO(

        // - Relativo a classe pai (Livro)

        @NotNull
        Long id_livro,

        // - Relativo a classe filha (LivroEbook)

        Short tamanho_arquivo,

        String formato_arquivo

) {
}
