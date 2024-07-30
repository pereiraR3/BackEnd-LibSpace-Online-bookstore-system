package com.websystem.libspace.domain.item_carrinho;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrinhoPK implements Serializable {

    private Long id;
    private Long carrinho;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCarrinhoPK that = (ItemCarrinhoPK) o;
        return Objects.equals(id, that.id) && Objects.equals(carrinho, that.carrinho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carrinho);
    }

}
