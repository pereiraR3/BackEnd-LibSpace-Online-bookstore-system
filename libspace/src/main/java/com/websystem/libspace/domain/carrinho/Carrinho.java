package com.websystem.libspace.domain.carrinho;

import com.websystem.libspace.domain.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "carrinho")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate data_criacao;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    public Carrinho(CarrinhoRequestDTO body, User user){
        this.status = body.status();
        this.data_criacao = body.data_criacao();
        this.user = user;
    }

}
