package com.websystem.libspace.domain.livro_possui_categoria;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class LivroPossuiCategoriaPK implements Serializable {

    private Long livro;
    private Long categoria;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivroPossuiCategoriaPK that = (LivroPossuiCategoriaPK) o;
        return Objects.equals(livro, that.livro) && Objects.equals(categoria, that.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro, categoria);
    }

}
