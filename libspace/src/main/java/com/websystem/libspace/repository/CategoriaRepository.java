package com.websystem.libspace.repository;

import com.websystem.libspace.domain.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
