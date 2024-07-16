package com.websystem.libspace.domain.users;

public record UserResponseDTO(

        Long id,

        String cpf,

        String username,

        String password,

        String email,

        String url_foto,

        String url_website,

        String bio,

        UserRole role

) {

    public UserResponseDTO(User user){
       this(
               user.getId(),
               user.getCpf(),
               user.getUsername(),
               user.getPassword(),
               user.getEmail(),
               user.getUrl_foto(),
               user.getUrl_website(),
               user.getBio(),
               user.getRole()

       );
    }

}
