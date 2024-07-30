package com.websystem.libspace.domain.item_carrinho;

import com.websystem.libspace.domain.carrinho.Carrinho;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_carrinho")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ItemCarrinhoPK.class)
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_carrinho", referencedColumnName = "id")
    private Carrinho carrinho;

    @Column(name = "quantidade", nullable = false)
    private Short quantidade;

    @Column(name = "preco_unitario", nullable = false)
    private Float preco_unitario;

    public ItemCarrinho(ItemCarrinhoRequestDTO body, Carrinho carrinho){
        this.carrinho = carrinho;
        this.quantidade = body.quantidade();
        this.preco_unitario = body.preco_unitario();
    }

}
