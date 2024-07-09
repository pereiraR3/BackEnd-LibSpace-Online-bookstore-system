package com.websystem.libspace.domain.editora;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Editora")
@Table(name = "editora")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpnj", nullable = false)
    private Long cnpj;

    @Column(name = "cep", nullable = false)
    private Long cep;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "email", nullable = false)
    private String email;

    private String url_website;

    public Editora(EditoraRequestDTO body){

        this.nome = body.nome();
        this.cnpj = body.cnpj();
        this.cep = body.cep();
        this.telefone = body.telefone();
        this.email = body.email();
        this.url_website = body.url_website();

    }

    public void update(EditoraUpdateDTO updateDTO) {

        if(updateDTO.nome() != null)
            this.nome = updateDTO.nome();

        if(updateDTO.cep() != null)
            this.cep = updateDTO.cep();

        if(updateDTO.telefone() != null)
            this.telefone = updateDTO.telefone();

        if(updateDTO.email() != null)
            this.email = updateDTO.email();

    }
}
