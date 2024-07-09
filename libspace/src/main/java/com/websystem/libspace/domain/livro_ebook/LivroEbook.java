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
public class LivroEbook extends Livro{

    @Column(name = "numero_de_paginas", nullable = false)
    Short numero_de_paginas;

    @Column(name = "peso", nullable = false)
    Short peso;

    @Column(name = "tipo_capa", nullable = false)
    String tipo_capa;

    @Column(name = "dimensao_altura", nullable = false)
    Short dimensao_altura;

    @Column(name = "dimensao_largura", nullable = false)
    Short dimensao_largura;

    @Column(name = "dimensao_profundidade", nullable = false)
    Short dimensao_profundidade;

    public LivroEbook(LivroEbookRequestDTO body, Editora editora){

        super(new LivroRequestDTO(
                body.id_editora(),
                body.preco(),
                body.titulo(),
                body.quantidade(),
                body.autor_nome(),
                body.ano_publicacao(),
                body.capa_url()
        ), editora);

        this.numero_de_paginas = body.numero_de_paginas();
        this.peso = body.peso();
        this.tipo_capa = body.tipo_capa();
        this.dimensao_altura = body.dimensao_altura();
        this.dimensao_largura = body.dimensao_largura();
        this.dimensao_profundidade = body.dimensao_profundidade();

    }

    public void update(LivroEbookUpdateDTO updateDTO){

    }

}

