package com.websystem.libspace.domain.livro_audiobook;

public record LivroAudiobookResponseDTO(

        // - Relativo a classe pai (Livro)

        Long id,

        // - Relativo a classe filho (LivroAudiobook)

        Short tamanho_arquivo,

        Short formato_arquivo,

        String narrador,

        String url_download

) {

    public LivroAudiobookResponseDTO(LivroAudiobook livroAudiobook){
        this(

                livroAudiobook.getId(),
                livroAudiobook.getTamanho_arquivo(),
                livroAudiobook.getFormato_arquivo(),
                livroAudiobook.getNarrador(),
                livroAudiobook.getUrl_download()

        );
    }

}
