package com.websystem.libspace.domain.livro;

import com.websystem.libspace.domain.genero.GeneroResponseDTO;

import java.util.List;

public record LivroWithGenerosListeningDTO(

        Long idLivro,

        Long id_editora,

        Double preco_unitario,

        Double preco_acumulado,

        String titulo,

        Short quantidade,

        List<GeneroResponseDTO> generoResponseDTOList

) {
}
