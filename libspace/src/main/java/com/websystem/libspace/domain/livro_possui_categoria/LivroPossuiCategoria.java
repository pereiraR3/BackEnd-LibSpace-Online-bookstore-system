package com.websystem.libspace.domain.livro_possui_categoria;

import com.websystem.libspace.domain.categoria.Categoria;
import com.websystem.libspace.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "livro_possui_categoria")
@Getter
@Setter
@NoArgsConstructor
@IdClass(LivroPossuiCategoriaPK.class)
public class LivroPossuiCategoria {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_livro", referencedColumnName = "id")
    private Livro livro;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    private Categoria categoria;

    public LivroPossuiCategoria(Livro livro, Categoria categoria){
        this.livro = livro;
        this.categoria = categoria;
    }

}
