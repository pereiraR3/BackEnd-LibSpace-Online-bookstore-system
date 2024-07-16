package com.websystem.libspace.domain.categoria;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "editora")
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

    public Categoria(CategoriaRequestDTO body){
        this.nome = body.nome();
    }

    public void update(CategoriaUpdateDTO updateDTO){
        if(updateDTO.nome() != null)
            this.nome = updateDTO.nome();
    }

}
