package com.websystem.libspace.domain.editora;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Add validacao em telefone e email - AJUSTAR
public record EditoraUpdateDTO(

        @NotNull
        Long id,

        @Size(max = 120)
        String nome,

        Long cep,

        @Size(max = 11)
        String telefone,

        @Size(max = 120)
        String email

) {

}
