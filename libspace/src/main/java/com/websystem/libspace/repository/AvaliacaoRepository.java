package com.websystem.libspace.repository;

import com.websystem.libspace.domain.avaliacao.Avaliacao;
import com.websystem.libspace.domain.avaliacao.AvaliacaoPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, AvaliacaoPK> {


    Optional<Avaliacao> findById(Long id);

    void deleteById(Long id);
}
