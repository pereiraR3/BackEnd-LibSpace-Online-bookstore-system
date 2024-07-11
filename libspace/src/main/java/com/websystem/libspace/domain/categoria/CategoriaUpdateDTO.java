package com.websystem.libspace.domain.categoria;

import jakarta.validation.constraints.NotNull;

public record CategoriaUpdateDTO(

        @NotNull
        Long id,

        String nome
        
) {
}
