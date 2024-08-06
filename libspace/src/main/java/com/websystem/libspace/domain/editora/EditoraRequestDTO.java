package com.websystem.libspace.domain.editora;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record EditoraRequestDTO(

        @NotBlank
        @Size(max = 120)
        String nome,

        @NotNull
        Long cnpj,

        @NotNull
        Long cep,

        @NotBlank
        @Size(max = 11)
        String telefone,

        @NotBlank
        @Size(max = 120)
        String email,

        @Size(max = 500)
        String url_website

) {
}
