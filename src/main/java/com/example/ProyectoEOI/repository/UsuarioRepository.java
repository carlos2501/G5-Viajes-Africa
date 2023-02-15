package com.example.ProyectoEOI.repository;

import com.example.ProyectoEOI.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByEmail(String email);

    Usuario findUsuarioByEmail(String email);
    @Query("Select count(id) from Usuario where email= ?1 and password = ?2")
    Integer repValidarPassword(String email, String password);

}