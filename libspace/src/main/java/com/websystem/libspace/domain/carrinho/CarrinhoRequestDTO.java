package com.websystem.libspace.domain.carrinho;

import jakarta.validation.constraints.NotNull;


public record CarrinhoRequestDTO(

    @NotNull
    Boolean status,

    @NotNull
    Long id_user

) {
}
