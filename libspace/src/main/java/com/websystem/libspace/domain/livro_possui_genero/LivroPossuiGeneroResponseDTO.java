package com.websystem.libspace.domain.livro_possui_genero;

import com.websystem.libspace.domain.livro_possui_categoria.LivroPossuiCategoria;

public record LivroPossuiGeneroResponseDTO(

        Long id_livro,

        Long id_genero

) {


    public LivroPossuiGeneroResponseDTO(LivroPossuiGenero body){
        this(
                body.getLivro().getId(),
                body.getGenero().getId()
        );
    }
}
