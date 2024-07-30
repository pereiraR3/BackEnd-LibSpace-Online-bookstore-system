package com.websystem.libspace.domain.item_carrinho;

import jakarta.validation.constraints.NotNull;

public record ItemCarrinhoRequestDTO(

        @NotNull
        Long id_carrinho,

        @NotNull
        Short quantidade,

        @NotNull
        Float preco_unitario

) {
}
