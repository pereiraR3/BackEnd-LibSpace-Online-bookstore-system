package com.websystem.libspace.domain.livro;

import com.websystem.libspace.domain.impostos.ImpostosResponseDTO;

import java.util.List;

public record LivroImpostosListeningDTO(

        Long idLivro,

        Long id_editora,

        Double preco_unitario,

        Double preco_acumulado,

        String titulo,

        Short quantidade,

        List<ImpostosResponseDTO> impostosResponseDTO

) {
}
