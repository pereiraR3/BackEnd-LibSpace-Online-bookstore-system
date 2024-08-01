package com.websystem.libspace.domain.oferta;

import jakarta.validation.constraints.NotNull;

public record OfertaResponseDTO(

        @NotNull
        Long id,

        Long id_livro,

        Long id_editora,

        double preco_revenda,

        double desconto

) {
    public OfertaResponseDTO(Oferta oferta) {

        this(
                oferta.getId(),
                oferta.getLivro().getId(),
                oferta.getEditora().getId(),
                oferta.getPreco_revenda(),
                oferta.getDesconto()
        );
    }
}
