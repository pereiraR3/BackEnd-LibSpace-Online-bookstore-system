package com.websystem.libspace.domain.editora;

import com.websystem.libspace.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "editora")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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

    @OneToMany(mappedBy = "editora")
    private Set<Livro> livros = new HashSet<>();

    public Editora(EditoraRequestDTO body){

        this.nome = body.nome();
        this.cnpj = body.cnpj();
        this.cep = body.cep();
        this.telefone = body.telefone();
        this.email = body.email();
        this.url_website = body.url_website();

    }

}
