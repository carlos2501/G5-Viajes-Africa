package com.example.ProyectoEOI.repository;

import com.example.ProyectoEOI.model.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
}
