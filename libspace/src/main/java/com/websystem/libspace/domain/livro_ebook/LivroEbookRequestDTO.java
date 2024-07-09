package com.websystem.libspace.domain.livro_ebook;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LivroEbookRequestDTO(


        // - Relativo a classe pai (Livro)

        @NotNull
        Long id_editora,

        @NotNull
        Double preco,

        @NotBlank
        @Size(max = 120)
        String titulo,

        @NotNull
        Short quantidade,

        @NotBlank
        @Size(max = 160)
        String autor_nome,

        @NotNull
        Short ano_publicacao,

        @NotBlank
        String capa_url,

        // - Relativo a classe filha (LivroEbook)





) {
}
