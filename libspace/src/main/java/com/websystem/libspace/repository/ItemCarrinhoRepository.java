package com.websystem.libspace.repository;

import com.websystem.libspace.domain.item_carrinho.ItemCarrinho;
import com.websystem.libspace.domain.item_carrinho.ItemCarrinhoPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, ItemCarrinhoPK> {

    void deleteById(Long id);

    Optional<ItemCarrinho> findById(Long id);
}
