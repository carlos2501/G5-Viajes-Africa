package com.example.ProyectoEOI.repository;

import com.example.ProyectoEOI.model.Role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    //Añadir findall
    Page<Role> findAll(Pageable pageable);
}
