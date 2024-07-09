package com.websystem.libspace.domain.editora;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EditoraListeningDTO(

        Long id,

        String nome,

        Long cep,

        String telefone,

        String email,

        String url_website

) {
}
