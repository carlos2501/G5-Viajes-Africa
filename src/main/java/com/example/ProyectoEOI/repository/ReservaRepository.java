package com.example.ProyectoEOI.repository;

import com.example.ProyectoEOI.model.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    Page<Reserva> findByUsuarioId(Long userId, Pageable pageable);

    List<Reserva> findByUsuarioId(Long userId);
}
