package com.websystem.libspace.domain.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(

        @NotBlank
        @Size(max = 11)
        String cpf,

        @NotBlank
        @Size(max = 60)
        String username,

        @NotBlank
        @Size(max = 80)
        String password,

        @NotBlank
        @Size(max = 120)
        String email,

        @Size(max = 1000)
        String url_foto,

        @Size(max = 1000)
        String url_website,

        @Size(max = 2000)
        String bio,

        @NotNull
        UserRole role

) {
}
