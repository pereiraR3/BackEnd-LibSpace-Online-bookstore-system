package com.websystem.libspace.repository;

import com.websystem.libspace.domain.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l, i " +
            "FROM Livro l " +
            "JOIN l.impostos i")
    List<Object[]> getLivroWithImpostos();

    @Query("SELECT l, i " +
            "FROM Livro l " +
            "JOIN l.impostos i " +
            "WHERE l.id = :idLivro ")
    Object[] getOnlyLivroWithImpostos(@Param("idLivro") Long idLivro);

    @Query("SELECT l, c " +
            "FROM Livro l " +
            "JOIN l.categorias c ")
    List<Object[]> getLivroWithCategorias();

    @Query("SELECT l, c " +
            "FROM Livro l " +
            "JOIN l.categorias c " +
            "WHERE l.id = :idLivro ")
    Object[] getOnlyLivroWithCategorias(@Param("idLivro") Long idLivro);

    @Query("SELECT l, g " +
            "FROM Livro l " +
            "JOIN l.generos g ")
    List<Object[]> getLivroWithGeneros();

    @Query("SELECT l, g " +
            "FROM Livro l " +
            "JOIN l.generos g " +
            "WHERE l.id = :idLivro ")
    Object[] getOnlyLivroWithGeneros(@Param("idLivro") Long idLivro);

    @Query("SELECT l, g, c " +
            "FROM Livro l " +
            "JOIN l.generos g " +
            "JOIN l.categorias c ")
    List<Object[]> getAllLivroWithGenerosWIthCategorias();


}
