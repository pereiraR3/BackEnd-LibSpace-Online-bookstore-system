package com.websystem.libspace.domain.genero;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GeneroRequestDTO(

        @NotBlank
        @Size(max = 60)
        String nome

) {
}
