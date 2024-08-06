package com.websystem.libspace.repository;

import com.websystem.libspace.domain.editora.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EditoraRepository extends JpaRepository<Editora, Long> {

    Optional<Editora> findByCnpj(Long cnpj);

    Optional<Editora> findByEmail(String email);

}
