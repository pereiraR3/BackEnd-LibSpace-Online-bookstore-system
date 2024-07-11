package com.websystem.libspace.domain.livro_audiobook;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LivroAudiobookUpdateDTO(

        @NotNull
        Long id_livro,

        // - Relativo a classe filho (LivroAudiobook)

        Short tamanho_arquivo,

        Short formato_arquivo,

        @Size(max = 120)
        String narrador,

        String url_download

) {
}
