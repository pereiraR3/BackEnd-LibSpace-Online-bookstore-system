package com.websystem.libspace.domain.editora;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EditoraResponseDTO(

        Long id,

        String nome,

        Long cnpj,

        Long cep,

        String telefone,

        String email,

        String url_website

) {

    public EditoraResponseDTO(Editora dadosEditora){

        this(
                dadosEditora.getId(),
                dadosEditora.getNome(),
                dadosEditora.getCnpj(),
                dadosEditora.getCep(),
                dadosEditora.getTelefone(),
                dadosEditora.getEmail(),
                dadosEditora.getUrl_website()
        );

    }

}
