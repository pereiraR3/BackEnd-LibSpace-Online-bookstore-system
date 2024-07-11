package com.websystem.libspace.domain.livro_fisico;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LivroFisicoUpdateDTO(

        @NotNull
        Long id_livro,

        Short numero_de_paginas,

        Short peso,

        @Size(max = 60)
        String tipo_capa,

        Short dimensao_altura,

        Short dimensao_largura,

        Short dimensao_profundidade

) {
}
