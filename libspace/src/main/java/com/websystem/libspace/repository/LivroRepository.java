package com.websystem.libspace.repository;

import com.websystem.libspace.domain.livro.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LivroRepository extends PagingAndSortingRepository<Livro, Long> {

    Livro save(Livro livro);

    Livro findById(Long id);

    Page<Livro> findAll(Pageable pageable);

    void deleteById(Long id);

    void deleteAll();

    Livro findByTitulo(String harryPotter);
}
