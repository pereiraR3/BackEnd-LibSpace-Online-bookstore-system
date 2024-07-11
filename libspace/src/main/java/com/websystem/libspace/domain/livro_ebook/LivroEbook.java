package com.websystem.libspace.domain.livro_ebook;


import com.websystem.libspace.domain.editora.Editora;
import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.livro.LivroRequestDTO;
import com.websystem.libspace.domain.livro.LivroUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "LivroEbook")
@Table(name = "livro_ebook")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LivroEbook{

    @Id
    @Column(name = "id_livro")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;

    @Column(name = "tamanho_arquivo", nullable = false)
    private Short tamanho_arquivo;

    @Column(name = "formato_arquivo", nullable = false)
    private String formato_arquivo;

    public LivroEbook(LivroEbookRequestDTO body, Livro livro){

        this.livro = livro;

        this.tamanho_arquivo = body.tamanho_arquivo();
        this.formato_arquivo = body.formato_arquivo();

    }

    /**
     * Não precisa de update, pois essa é uma informação elaborada pelo front ou back
     */

}

