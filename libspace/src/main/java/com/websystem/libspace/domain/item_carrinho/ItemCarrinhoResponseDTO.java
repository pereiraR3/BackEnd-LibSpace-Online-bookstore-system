package com.websystem.libspace.domain.item_carrinho;

public record ItemCarrinhoResponseDTO(

        Long id,

        Long id_carrinho,

        Short quantidade,

        Float preco_unitario

) {
}
