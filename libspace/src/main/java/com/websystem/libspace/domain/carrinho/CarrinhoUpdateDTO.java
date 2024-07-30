package com.websystem.libspace.domain.carrinho;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CarrinhoUpdateDTO(

        @NotNull
        Long id,

        Boolean status,

        @Future
        LocalDate data_criacao

) {
}
