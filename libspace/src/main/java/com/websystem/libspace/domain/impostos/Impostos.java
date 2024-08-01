package com.websystem.libspace.domain.impostos;

import com.websystem.libspace.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "impostos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Impostos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false)
    private Integer codigo;

    @Column(name = "percentual", nullable = false)
    private double percentual;

    @ManyToMany(mappedBy = "impostos")
    private Set<Livro> livros;

    public Impostos(ImpostosRequestDTO body){
        this.codigo = body.codigo();
        this.percentual = body.percentual();
    }

}
