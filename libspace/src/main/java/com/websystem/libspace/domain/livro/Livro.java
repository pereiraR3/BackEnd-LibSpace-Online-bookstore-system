package com.websystem.libspace.domain.livro;

import com.websystem.libspace.domain.editora.Editora;
import com.websystem.libspace.domain.livro_ebook.LivroEbook;
import com.websystem.libspace.domain.livro_fisico.LivroFisico;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Livro")
@Table(name = "livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_editora", nullable = false)
    private Editora editora;

    @Column(nullable = false)
    private Double preco;

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

    public Livro(LivroRequestDTO body, Editora editora){
        this.editora = editora;
        this.preco = body.preco();
        this.titulo = body.titulo();
        this.quantidade = body.quantidade();
        this.autor_nome = body.autor_nome();
        this.ano_publicacao = body.ano_publicacao();
        this.capa_url = body.capa_url();
    }

    public void update(LivroUpdateDTO updateDTO) {

        if(updateDTO.preco() != null)
            this.preco = updateDTO.preco();

        if(updateDTO.quantidade() != null)
            this.quantidade = updateDTO.quantidade();

        if(updateDTO.autor_nome() != null)
            this.autor_nome = updateDTO.autor_nome();

        if(updateDTO.ano_publicacao() != null)
            this.ano_publicacao = updateDTO.ano_publicacao();

    }
}