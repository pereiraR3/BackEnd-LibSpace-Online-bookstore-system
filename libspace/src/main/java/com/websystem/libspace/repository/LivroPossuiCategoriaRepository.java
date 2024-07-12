package com.websystem.libspace.repository;

import com.websystem.libspace.domain.livro_possui_categoria.LivroPossuiCategoria;
import com.websystem.libspace.domain.livro_possui_categoria.LivroPossuiCategoriaPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroPossuiCategoriaRepository extends JpaRepository<LivroPossuiCategoria, LivroPossuiCategoriaPK> {


    void deleteByLivroIdByCategoriaId(Long idLivro, Long idCategoria);

    LivroPossuiCategoria findByLivroIdCategoriaId(Long idLivro, Long idCategoria);

    List<LivroPossuiCategoria> findByCategoriaId(Long idCategoria);

    List<LivroPossuiCategoria> findByLivroId(Long idLivro);
}
