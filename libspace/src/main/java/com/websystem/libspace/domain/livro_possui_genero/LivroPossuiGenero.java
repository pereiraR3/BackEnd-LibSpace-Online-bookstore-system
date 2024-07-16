package com.websystem.libspace.domain.livro_possui_genero;

import com.websystem.libspace.domain.genero.Genero;
import com.websystem.libspace.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "genero")
@Setter
@Getter
@NoArgsConstructor
@IdClass(LivroPossuiGeneroPK.class)
public class LivroPossuiGenero {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_livro", referencedColumnName = "id")
    private Livro livro;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_genero", referencedColumnName = "id")
    private Genero genero;

    public LivroPossuiGenero(Livro livro, Genero genero){
        this.livro = livro;
        this.genero = genero;
    }

}
