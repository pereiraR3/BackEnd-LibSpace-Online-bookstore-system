package com.websystem.libspace.domain.livro_audiobook;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LivroAudiobookRequestDTO(

        // - Relativo a classe pai (Livro)

        @NotNull
        Long id,

        // - Relativo a classe filho (LivroAudiobook)

        @NotNull
        Short tamanho_arquivo,

        @NotNull
        Short formato_arquivo,

        @NotBlank
        @Size(max = 120)
        String narrador,

        @NotBlank
        String url_download

) {
}
