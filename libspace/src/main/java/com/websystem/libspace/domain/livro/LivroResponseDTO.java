package com.websystem.libspace.domain.livro;

public record LivroResponseDTO(

        Long id_editora,

        Double preco,

        String titulo,

        Short quantidade,

        String autor_nome,

        Short ano_publicacao,

        String capa_url

) {

    public LivroResponseDTO(Livro dadosLivro){

        this(
                dadosLivro.getId_editora(),
                dadosLivro.getPreco(),
                dadosLivro.getTitulo(),
                dadosLivro.getQuantidade(),
                dadosLivro.getAutor_nome(),
                dadosLivro.getAno_publicacao(),
                dadosLivro.getCapa_url()
        );

    }

}
