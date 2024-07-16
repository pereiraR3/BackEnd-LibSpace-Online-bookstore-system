package com.websystem.libspace.repository;

import com.websystem.libspace.domain.livro_possui_genero.LivroPossuiGenero;
import com.websystem.libspace.domain.livro_possui_genero.LivroPossuiGeneroPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroPossuiGeneroRepository extends JpaRepository<LivroPossuiGenero, LivroPossuiGeneroPK> {

    void deleteByLivroIdAndGeneroId(Long idLivro, Long idGenero);

    LivroPossuiGenero findByLivroIdAndGeneroId(Long idLivro, Long idCategoria);

    List<LivroPossuiGenero> findAllByLivroId(Long idLivro);

    List<LivroPossuiGenero> findAllByGeneroId(Long idCategoria);

}
