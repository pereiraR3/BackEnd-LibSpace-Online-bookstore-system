package com.websystem.libspace.domain.oferta;

import com.websystem.libspace.domain.editora.Editora;
import com.websystem.libspace.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "oferta")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_livro", referencedColumnName = "id")
    private Livro livro;

    @Column(name  = "preco_revenda", nullable = false)
    private double preco_revenda;

    @Column(name = "desconto")
    private double desconto;

    public Oferta(Livro livro, Editora editora){
        this.livro = livro;
        this.preco_revenda = livro.getPreco_acumulado();
        this.desconto = 0.00;
    }

}
