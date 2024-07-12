package com.websystem.libspace.domain.livro_fisico;

import com.websystem.libspace.domain.livro.Livro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "livro_fisico")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LivroFisico{

    @Id
    @Column(name = "id_livro")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;

    @Column(name = "numero_de_paginas", nullable = false)
    private Short numero_de_paginas;

    @Column(name = "peso", nullable = false)
    private Short peso;

    @Column(name = "tipo_capa", nullable = false)
    private String tipo_capa;

    @Column(name = "dimensao_altura", nullable = false)
    private Short dimensao_altura;

    @Column(name = "dimensao_largura", nullable = false)
    private Short dimensao_largura;

    @Column(name = "dimensao_profundidade", nullable = false)
    private Short dimensao_profundidade;

    public LivroFisico(LivroFisicoRequestDTO body, Livro livro){

        this.livro = livro;

        this.numero_de_paginas = body.numero_de_paginas();
        this.peso = body.peso();
        this.tipo_capa = body.tipo_capa();
        this.dimensao_altura = body.dimensao_altura();
        this.dimensao_largura = body.dimensao_largura();
        this.dimensao_profundidade = body.dimensao_profundidade();

    }

    public void update(LivroFisicoUpdateDTO updateDTO){

        if(updateDTO.numero_de_paginas() != null)
            this.numero_de_paginas = updateDTO.numero_de_paginas();

        if(updateDTO.peso() != null)
            this.peso = updateDTO.peso();

        if(updateDTO.tipo_capa() != null)
            this.tipo_capa = updateDTO.tipo_capa();

        if(updateDTO.dimensao_altura() != null)
            this.dimensao_altura = updateDTO.dimensao_altura();

        if(updateDTO.dimensao_largura() != null)
            this.dimensao_altura = updateDTO.dimensao_altura();

        if(updateDTO.dimensao_profundidade() != null)
            this.dimensao_profundidade = updateDTO.dimensao_profundidade();

    }

}

