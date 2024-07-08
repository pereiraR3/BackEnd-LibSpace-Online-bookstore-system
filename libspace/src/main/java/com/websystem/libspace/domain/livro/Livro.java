package com.websystem.libspace.domain.livro;


import com.websystem.libspace.domain.editora.Editora;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Livro")
@Table(name = "livro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_editora", nullable = false)
    private Long id_editora;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "quantidade", nullable = false)
    private Short quantidade;

    @Column(name = "autor_nome", nullable = false)
    private String autor_nome;

    @Column(name = "ano_publicacao", nullable = false)
    private Short ano_publicacao;

    @Column(name = "capa_url", nullable = true)
    private String capa_url;

    public Livro(LivroRequestDTO body, Editora editora){

        this.id_editora = editora.getId();
        this.preco = body.preco();
        this.titulo = body.titulo();
        this.quantidade = body.quantidade();
        this.autor_nome = body.autor_nome();
        this.ano_publicacao = body.ano_publicacao();
        this.capa_url = body.capa_url(); // -> Mudar

    }

}
