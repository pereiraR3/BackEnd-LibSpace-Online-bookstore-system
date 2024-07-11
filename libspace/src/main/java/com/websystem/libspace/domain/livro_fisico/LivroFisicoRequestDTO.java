package com.websystem.libspace.domain.livro_fisico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LivroFisicoRequestDTO(

        // - Relativo a classe pai (Livro)

        @NotNull
        Long id,

        // - Relativo a classe filha (LivroFisico)

        @NotNull
        Short numero_de_paginas,

        @NotNull
        Short peso,

        @NotBlank
        @Size(max = 60)
        String tipo_capa,

        @NotNull
        Short dimensao_altura,

        @NotNull
        Short dimensao_largura,

        @NotNull
        Short dimensao_profundidade

) {
}
