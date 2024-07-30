package com.websystem.libspace.domain.carrinho;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CarrinhoRequestDTO(

    @NotNull
    Boolean status,

    @NotNull
    @Future
    LocalDate data_criacao,

    @NotNull
    Long id_user

) {
}
