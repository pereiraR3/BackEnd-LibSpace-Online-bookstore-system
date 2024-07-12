package com.websystem.libspace.domain.livro_audiobook;

import com.websystem.libspace.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "livro_audiobook")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroAudiobook {

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
    private Short formato_arquivo;

    @Column(name = "narrador", nullable = false)
    private String narrador;

    @Column(name = "url_download", nullable = false)
    private String url_download;

    public LivroAudiobook(LivroAudiobookRequestDTO body, Livro livro){

        this.livro = livro;
        this.tamanho_arquivo = body.tamanho_arquivo();
        this.formato_arquivo = body.formato_arquivo();
        this.narrador = body.narrador();
        this.url_download = body.url_download();

    }

    public void update(LivroAudiobookUpdateDTO updateDTO){

        if(updateDTO.tamanho_arquivo() != null)
            this.tamanho_arquivo = updateDTO.tamanho_arquivo();

        if(updateDTO.formato_arquivo() != null)
            this.formato_arquivo = updateDTO.formato_arquivo();

        if(updateDTO.narrador() != null)
            this.narrador = updateDTO.narrador();

        if(updateDTO.url_download() != null)
            this.url_download = updateDTO.url_download();

    }

}
