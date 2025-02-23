package com.websystem.libspace.domain.livro;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LivroUpdateDTO(

        @NotNull
        Long id,

        Double preco_unitario,

        Double preco_acumulado,

        @Size(max = 120)
        String titulo,

        Short quantidade,

        @Size(max = 160)
        String autor_nome,

        Short ano_publicacao,

        String capa_url
) {
}
