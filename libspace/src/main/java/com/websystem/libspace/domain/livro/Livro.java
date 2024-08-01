package com.websystem.libspace.domain.livro;

import com.websystem.libspace.domain.impostos.Impostos;
import com.websystem.libspace.functions.CreateTableOferta;
import com.websystem.libspace.domain.avaliacao.Avaliacao;
import com.websystem.libspace.domain.categoria.Categoria;
import com.websystem.libspace.domain.editora.Editora;
import com.websystem.libspace.domain.genero.Genero;
import com.websystem.libspace.domain.livro_ebook.LivroEbook;
import com.websystem.libspace.domain.livro_fisico.LivroFisico;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(CreateTableOferta.class)
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_editora", nullable = false)
    private Editora editora;

    @Column(nullable = false)
    private Double preco_unitario;

    @Column(nullable = false)
    private Double preco_acumulado;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Short quantidade;

    @Column(name = "autor_nome", nullable = false)
    private String autor_nome;

    @Column(name = "ano_publicacao", nullable = false)
    private Short ano_publicacao;

    @Column(name = "capa_url")
    private String capa_url;

    @OneToOne(mappedBy = "livro", cascade = CascadeType.ALL)
    private LivroEbook livroEbook;

    @OneToOne(mappedBy = "livro", cascade = CascadeType.ALL)
    private LivroFisico livroFisico;

    @ManyToMany
    @JoinTable(
            name = "livro_possui_categoria",
            joinColumns = @JoinColumn(name = "id_livro"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> categorias;

    @ManyToMany
    @JoinTable(
            name = "livro_possui_genero",
            joinColumns = @JoinColumn(name = "id_livro"),
            inverseJoinColumns = @JoinColumn(name = "id_genero")
    )
    private Set<Genero> generos;

    @ManyToMany
    @JoinTable(
            name = "impostos_pertencem_a_livro",
            joinColumns = @JoinColumn(name = "id_livro"),
            inverseJoinColumns = @JoinColumn(name = "id_impostos")
    )
    private Set<Impostos> impostos;

    @OneToMany
    private Set<Avaliacao> avaliacoes;

    public Livro(LivroRequestDTO body, Editora editora){
        this.editora = editora;
        this.preco_unitario = body.preco_unitario();
        this.preco_acumulado = null;
        this.titulo = body.titulo();
        this.quantidade = body.quantidade();
        this.autor_nome = body.autor_nome();
        this.ano_publicacao = body.ano_publicacao();
        this.capa_url = body.capa_url();
    }

}