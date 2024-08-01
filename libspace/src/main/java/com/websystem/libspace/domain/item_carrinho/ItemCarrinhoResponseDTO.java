package com.websystem.libspace.domain.item_carrinho;

public record ItemCarrinhoResponseDTO(

        Long id,

        Long id_carrinho,

        Short quantidade,

        Float preco_unitario

) {
    public ItemCarrinhoResponseDTO(ItemCarrinho itemCarrinho) {

        this(
                itemCarrinho.getId(),
                itemCarrinho.getCarrinho().getId(),
                itemCarrinho.getQuantidade(),
                itemCarrinho.getPreco_unitario()
        );

    }
}
