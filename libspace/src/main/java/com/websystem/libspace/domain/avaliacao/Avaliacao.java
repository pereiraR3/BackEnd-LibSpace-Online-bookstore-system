package com.websystem.libspace.domain.avaliacao;

import com.websystem.libspace.domain.livro.Livro;
import com.websystem.libspace.domain.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "editora")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

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

    public void update(AvaliacaoUpdateDTO updateDTO){
        if(updateDTO.comentario() != null)
            this.comentario = updateDTO.comentario();
        if(updateDTO.nota() != null)
            this.nota = updateDTO.nota();
    }

}
