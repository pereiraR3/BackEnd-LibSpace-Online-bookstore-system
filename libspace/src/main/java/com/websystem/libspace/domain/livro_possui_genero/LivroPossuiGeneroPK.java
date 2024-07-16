package com.websystem.libspace.domain.livro_possui_genero;

import com.websystem.libspace.domain.livro_possui_categoria.LivroPossuiCategoriaPK;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class LivroPossuiGeneroPK implements Serializable {


    private Long livro;
    private Long genero;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivroPossuiGeneroPK that = (LivroPossuiGeneroPK) o;
        return Objects.equals(livro, that.livro) && Objects.equals(genero, that.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro, genero);
    }

}
