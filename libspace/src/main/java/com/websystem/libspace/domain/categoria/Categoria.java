package com.websystem.libspace.domain.categoria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "editora")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    public Categoria(CategoriaRequestDTO body){
        this.nome = body.nome();
    }

    public void update(CategoriaUpdateDTO updateDTO){
        if(updateDTO.nome() != null)
            this.nome = updateDTO.nome();
    }

}
