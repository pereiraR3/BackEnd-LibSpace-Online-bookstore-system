package com.websystem.libspace.domain.genero;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GeneroResponseDTO(

        Long id,

        String nome

) {

    public GeneroResponseDTO(Genero genero){
        this(
                genero.getId(),
                genero.getNome()
        );
    }

}
