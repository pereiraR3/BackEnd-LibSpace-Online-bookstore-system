package com.websystem.libspace.domain.livro;

public record LivroResponseDTO(

        Long id,

        Long id_editora,

        Double preco_unitario,

        Double preco_acumulado,

        String titulo,

        Short quantidade,

        String autor_nome,

        Short ano_publicacao,

        String capa_url

) {

    public LivroResponseDTO(Livro dadosLivro){

        this(
                dadosLivro.getId(),
                dadosLivro.getEditora().getId(),
                dadosLivro.getPreco_unitario(),
                dadosLivro.getPreco_acumulado(),
                dadosLivro.getTitulo(),
                dadosLivro.getQuantidade(),
                dadosLivro.getAutor_nome(),
                dadosLivro.getAno_publicacao(),
                dadosLivro.getCapa_url()
        );

    }

}
