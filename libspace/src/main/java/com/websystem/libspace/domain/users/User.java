package com.websystem.libspace.domain.users;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "url_foto")
    private String url_foto;

    @Column(name = "url_website")
    private String url_website;

    @Column(name = "bio")
    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    public User(UserRequestDTO body, String passwordEncripty){

        this.cpf = body.cpf();
        this.username = body.username();
        this.password = passwordEncripty;
        this.email = body.email();
        this.role = body.role();

        if(body.url_foto() != null)
            this.url_foto = body.url_foto();

        if(body.url_website() != null)
            this.url_website = body.url_website();

        if(body.bio() != null)
            this.bio = body.bio();

    }

}
