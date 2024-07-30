package com.websystem.libspace.repository;

import com.websystem.libspace.domain.oferta.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {
}
