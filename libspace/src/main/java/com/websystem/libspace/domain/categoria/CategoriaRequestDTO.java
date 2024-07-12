package com.websystem.libspace.domain.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequestDTO(

        @NotBlank
        @Size(max = 60)
        String nome

) {
}
