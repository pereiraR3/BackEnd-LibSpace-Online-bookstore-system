package com.websystem.libspace.domain.livro;

public record LivroRequestDTO(

    Long id_editora,

    Double preco,

    String titulo,

    Short quantidade,

    String autor_nome,

    Short ano_publicacao,

    String capa_url

) {
}
