package com.websystem.libspace.domain.categoria;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoriaUpdateDTO(

        @NotNull
        Long id,

        @Size(max = 100)
        String nome
        
) {
}
