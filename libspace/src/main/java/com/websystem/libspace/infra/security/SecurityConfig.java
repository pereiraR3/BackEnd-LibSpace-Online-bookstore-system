package com.websystem.libspace.infra.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import io.swagger.v3.oas.models.PathItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${jwt.public.key}")
    private RSAPublicKey key;

    @Value("${jwt.private.key}")
    private RSAPrivateKey priv;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth

                                // Controller -> AuthenticationController

                                .requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll()

                                // Controller -> AvaliacaoController

                                .requestMatchers(HttpMethod.POST, "/avaliacao/create").permitAll()
                                .requestMatchers(HttpMethod.GET, "/avaliacao/Livro/{idLivro}/User/{idUser}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/avaliacao/findAll").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/avaliacao/update").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/avaliacao/Livro/{idLivro}/User/{idUser}").permitAll()

                                // Controller -> CategoriaController

                                .requestMatchers(HttpMethod.POST, "/categoria/create").permitAll()
                                .requestMatchers(HttpMethod.GET, "/categoria/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/categoria/findAll").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/categoria/update").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/categoria/{id}").permitAll()

                                // Controller -> EditoraController

                                .requestMatchers(HttpMethod.POST, "/editora/create").permitAll()
                                .requestMatchers(HttpMethod.GET, "/editora/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/editora/findAll").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/editora/update").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/editora/{id}").permitAll()

                                // Controller -> GeneroController

                                .requestMatchers(HttpMethod.POST, "/genero/create").permitAll()
                                .requestMatchers(HttpMethod.GET, "/genero/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/genero/findAll").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/genero/update").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/genero/{id}").permitAll()

                                // Controller -> LivroAudioBookController

                                .requestMatchers(HttpMethod.POST, "/livro_audiobook/create").permitAll()
                                .requestMatchers(HttpMethod.GET, "/livro_audiobook/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/livro_audiobook/findAll").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/livro_audiobook/update").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/livro_audiobook/{id}").permitAll()

                                // Controller -> LivroController

                                .requestMatchers(HttpMethod.POST, "/livro/create").permitAll()
                                .requestMatchers(HttpMethod.GET, "/livro/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/livro/findAll").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/livro/update").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/livro/{id}").permitAll()

                                // Controller -> LivroEbookController

                                .requestMatchers(HttpMethod.POST, "/livro_ebook/create").permitAll()
                                .requestMatchers(HttpMethod.GET, "/livro_ebook/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/livro_ebook/findAll").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/livro_ebook/update").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/livro_ebook/{id}").permitAll()

                                // Controller -> LivroFisicoController

                                .requestMatchers(HttpMethod.POST, "/livro_fisico/create").permitAll()
                                .requestMatchers(HttpMethod.GET, "/livro_fisico/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/livro_fisico/findAll").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/livro_fisico/update").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/livro_fisico/{id}").permitAll()

                                // Controller -> UserController

                                .requestMatchers(HttpMethod.POST, "/user/create").permitAll()
                                .requestMatchers(HttpMethod.GET, "/user/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/user/findAll").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/user/update").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/user/{id}").permitAll()

                                // Controller -> CarrinhoController

                                .requestMatchers(HttpMethod.POST, "/carrinho/create").permitAll()
                                .requestMatchers(HttpMethod.GET, "/carrinho/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/carrinho/findAll").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/carrinho/findAll").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/carrinho/{id}").permitAll()

                                // Controller -> ItemCarrinhoController

                                .requestMatchers(HttpMethod.POST, "/item_carrinho/create").permitAll()
                                .requestMatchers(HttpMethod.GET, "/item_carrinho/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/item_carrinho/findAll").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/item_carrinho/findAll").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/item_carrinho/{id}").permitAll()

                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(
                        conf -> conf.jwt(Customizer.withDefaults())
                );

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(key).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        var jwk = new RSAKey.Builder(key).privateKey(priv).build();
        var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
