package com.example.ProyectoEOI.model;

import com.example.ProyectoEOI.constants.Dificultad;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "excursion")
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detalles;
    private String duracion;
    private String informacion;
    private String itinerario;
    private Integer maxPersonas;
    private String nombre;
    private Double precio;
    @OneToMany (mappedBy = "excursion")
    private Set<Opinion> opiniones;
    @OneToMany (mappedBy = "excursion")
    private Set<Foto>fotos;

}
