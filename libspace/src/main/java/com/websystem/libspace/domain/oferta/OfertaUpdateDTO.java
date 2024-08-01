package com.websystem.libspace.domain.oferta;

import jakarta.validation.constraints.NotNull;

public record OfertaUpdateDTO(

        @NotNull
        Long id,

        double preco_revenda,

        double desconto

) {
}
