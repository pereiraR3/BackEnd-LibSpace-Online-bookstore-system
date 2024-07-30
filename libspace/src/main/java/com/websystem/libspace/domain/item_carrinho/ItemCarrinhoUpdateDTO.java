package com.websystem.libspace.domain.item_carrinho;

import jakarta.validation.constraints.NotNull;

public record ItemCarrinhoUpdateDTO(

        @NotNull
        Long id,

        Short quantidade

) {
}
