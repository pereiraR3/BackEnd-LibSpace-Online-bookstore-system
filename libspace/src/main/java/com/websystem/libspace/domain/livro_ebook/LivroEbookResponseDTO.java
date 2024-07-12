package com.websystem.libspace.domain.livro_ebook;

public record LivroEbookResponseDTO(

        // -> Relativo a classe pai (Livro)

        Long id_livro,

        // -> Relativo a classe filha (LivroEbook)

        Short tamanho_arquivo,

        String formato_arquivo

) {

    public LivroEbookResponseDTO(LivroEbook livroEbook){
        this(

                livroEbook.getLivro().getId(),
                livroEbook.getTamanho_arquivo(),
                livroEbook.getFormato_arquivo()
        );
    }

}
