package com.websystem.libspace.domain.genero;

import jakarta.persistence.*;
import lombok.*;

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

    public Genero (GeneroRequestDTO body){
        this.nome = body.nome();
    }

    public void update(GeneroUpdateDTO requestDTO){
        if(requestDTO.nome() != null)
            this.nome = requestDTO.nome();
    }

}
