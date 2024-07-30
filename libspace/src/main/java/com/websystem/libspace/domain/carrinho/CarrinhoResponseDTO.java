package com.websystem.libspace.domain.carrinho;

import java.time.LocalDate;

public record CarrinhoResponseDTO(

        Long id,

        Boolean status,

        LocalDate data_criacao,

        Long id_user

) {

    public CarrinhoResponseDTO(Carrinho carrinho){
        this(
                carrinho.getId(),
                carrinho.getStatus(),
                carrinho.getData_criacao(),
                carrinho.getUser().getId()
        );
    }

}
