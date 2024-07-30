package com.websystem.libspace.domain.users;

public record UserResponseDTO(

        Long id,

        String username,

        String email,

        String url_foto,

        String url_website,

        String bio,

        UserRole role

) {

    public UserResponseDTO(User user){
       this(
               user.getId(),
               user.getUsername(),
               user.getEmail(),
               user.getUrl_foto(),
               user.getUrl_website(),
               user.getBio(),
               user.getRole()

       );
    }

}
