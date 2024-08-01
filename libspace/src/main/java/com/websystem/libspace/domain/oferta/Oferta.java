package com.websystem.libspace.domain.oferta;

import com.websystem.libspace.domain.editora.Editora;
import com.websystem.libspace.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "oferta")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_livro", referencedColumnName = "id")
    private Livro livro;

    @OneToOne
    @JoinColumn(name = "id_editora", referencedColumnName = "id")
    private Editora editora;

    @Column(name  = "preco_revenda", nullable = false)
    private double preco_revenda;

    @Column(name = "desconto", nullable = true)
    private double desconto;

    public Oferta(Livro livro, Editora editora){
        this.editora = editora;
        this.livro = livro;
        this.preco_revenda = livro.getPreco_acumulado();
    }

}
