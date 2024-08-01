package com.websystem.libspace.domain.livro;

import com.websystem.libspace.domain.categoria.CategoriaResponseDTO;

import java.util.List;

public record LivroCategoriaListeningDTO(

        Long idLivro,

        Long id_editora,

        Double preco_unitario,

        Double preco_acumulado,

        String titulo,

        Short quantidade,

        List<CategoriaResponseDTO> categoriaResponseDTOS


) {
}
