package com.websystem.libspace.domain.impostos;

import jakarta.validation.constraints.NotNull;

public record ImpostosRequestDTO(

        @NotNull
        Integer codigo,

        @NotNull
        double percentual

) {
}
