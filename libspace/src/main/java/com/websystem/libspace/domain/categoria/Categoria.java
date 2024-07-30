package com.websystem.libspace.domain.categoria;

import com.websystem.libspace.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @ManyToMany(mappedBy = "categorias")
    private Set<Livro> livros;

    public Categoria(CategoriaRequestDTO body){
        this.nome = body.nome();
    }


}
