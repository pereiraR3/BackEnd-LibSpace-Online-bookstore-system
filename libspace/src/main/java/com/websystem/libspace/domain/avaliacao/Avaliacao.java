package com.websystem.libspace.domain.avaliacao;

import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "avaliacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AvaliacaoPK.class)
public class Avaliacao {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "nota", nullable = false)
    private Short nota;

    @Column(name = "comentario", nullable = false)
    private String comentario;

    @Column(name = "data_avaliacao", nullable = false)
    private LocalDate dataAvaliacao;

    public Avaliacao(AvaliacaoRequestDTO body, Livro livro, User user){
        this.livro = livro;
        this.user = user;
        this.nota = body.nota();
        this.comentario = body.comentario();
    }



}
