package com.websystem.libspace.domain.editora;

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
