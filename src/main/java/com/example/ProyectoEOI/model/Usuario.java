package com.example.ProyectoEOI.model;

import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;
    private String password;
    private LocalDate fechaInsert;
    private LocalDate fechaAct;
    @ColumnDefault("true")
    private boolean activo;

    @OneToOne
    private Foto foto;

    @OneToMany (mappedBy = "usuario")
    private Set<MedioPago> medioPagos;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private Set<Reserva> reservas;

    @OneToMany(mappedBy= "usuario", fetch=FetchType.LAZY)
    private Set <Opinion> opiniones;


}

