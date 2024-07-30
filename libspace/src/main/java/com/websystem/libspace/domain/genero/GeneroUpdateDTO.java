package com.websystem.libspace.domain.genero;

import jakarta.validation.constraints.NotNull;

public record GeneroUpdateDTO(

        @NotNull
        Long id,

        String nome

) {
}
