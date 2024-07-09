package com.websystem.libspace.domain.livro_fisico;

public record LivroFisicoResponseDTO(

        // -> Relativo a classe pai (Livro)

        Long id,

        Long id_editora,

        Double preco,

        String titulo,

        Short quantidade,

        String autor_nome,

        Short ano_publicacao,

        String capa_url,

        // -> Relativo a classe filha (LivroFisico)

        Short numero_de_paginas,

        Short peso,

        String tipo_capa,

        Short dimensao_altura,

        Short dimensao_largura,

        Short dimensao_profundidade

) {

    public LivroFisicoResponseDTO(LivroFisico dadosLivroFisico){
        this(

                dadosLivroFisico.getId(),
                dadosLivroFisico.getEditora().getId(),
                dadosLivroFisico.getPreco(),
                dadosLivroFisico.getTitulo(),
                dadosLivroFisico.getQuantidade(),
                dadosLivroFisico.getAutor_nome(),
                dadosLivroFisico.getAno_publicacao(),
                dadosLivroFisico.getCapa_url(),

                dadosLivroFisico.numero_de_paginas,
                dadosLivroFisico.peso,
                dadosLivroFisico.tipo_capa,
                dadosLivroFisico.dimensao_altura,
                dadosLivroFisico.dimensao_largura,
                dadosLivroFisico.dimensao_profundidade

        );
    }

}
