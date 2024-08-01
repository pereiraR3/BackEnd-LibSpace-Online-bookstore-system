package com.websystem.libspace.domain.impostos;

import jakarta.validation.constraints.NotNull;

public record ImpostosUpdateDTO(

        @NotNull
        Long id,

        Integer codigo,

        double percentual

) {
}
