package com.websystem.libspace.domain.livro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LivroRequestDTO(


    @NotNull
    Long id_editora,

    @NotNull
    Double preco,

    @NotBlank
    @Size(max = 120)
    String titulo,

    @NotNull
    Short quantidade,

    @NotBlank
    @Size(max = 160)
    String autor_nome,

    @NotNull
    Short ano_publicacao,

    @NotBlank
    String capa_url

) {
}
