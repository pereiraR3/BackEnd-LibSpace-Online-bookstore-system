package com.websystem.libspace.infra.security;

public record AuthRequest(

        String username,

        String password

) {
}
