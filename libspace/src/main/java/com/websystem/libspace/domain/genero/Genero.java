package com.websystem.libspace.domain.genero;

import com.websystem.libspace.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "genero")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "generos")
    private Set<Livro> livros;

    public Genero (GeneroRequestDTO body){
        this.nome = body.nome();
    }

}
