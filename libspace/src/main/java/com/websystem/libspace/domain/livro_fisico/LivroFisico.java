package com.websystem.libspace.domain.livro_fisico;


import com.websystem.libspace.domain.editora.Editora;
import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.livro.LivroRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "LivroFisico")
@Table(name = "livro_fisico")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LivroFisico extends Livro{

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

    public LivroFisico(LivroFisicoRequestDTO body, Editora editora, Boolean statusCreateLivro){

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



}

