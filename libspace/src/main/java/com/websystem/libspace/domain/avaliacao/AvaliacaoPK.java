package com.websystem.libspace.domain.avaliacao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoPK implements Serializable {

    private Long livro;
    private Long user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvaliacaoPK that = (AvaliacaoPK) o;
        return Objects.equals(user, that.user) && Objects.equals(livro, that.livro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, livro);
    }

}
