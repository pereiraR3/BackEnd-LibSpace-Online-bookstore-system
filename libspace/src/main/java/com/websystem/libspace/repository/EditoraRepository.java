package com.websystem.libspace.repository;

import com.websystem.libspace.domain.editora.Editora;
import com.websystem.libspace.domain.livro.LivroListeningDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EditoraRepository extends JpaRepository<Editora, Long> {


    Optional<Editora> findByCnpj(Long cnpj);

    Optional<Editora> findByEmail(String email);

}
