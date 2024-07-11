package com.websystem.libspace.domain.livro_fisico;

public record LivroFisicoResponseDTO(

        // -> Relativo a classe pai (Livro)

        Long id_livro,

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
                dadosLivroFisico.getNumero_de_paginas(),
                dadosLivroFisico.getPeso(),
                dadosLivroFisico.getTipo_capa(),
                dadosLivroFisico.getDimensao_altura(),
                dadosLivroFisico.getDimensao_largura(),
                dadosLivroFisico.getDimensao_profundidade()

        );
    }

}
