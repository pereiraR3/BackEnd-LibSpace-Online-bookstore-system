package com.websystem.libspace.domain.livro_possui_categoria;

public record LivroPossuiCategoriaResponseDTO(

        Long id_livro,

        Long id_categoria

) {

    public LivroPossuiCategoriaResponseDTO(LivroPossuiCategoria body){
        this(
                body.getLivro().getId(),
                body.getCategoria().getId()
        );
    }

}
