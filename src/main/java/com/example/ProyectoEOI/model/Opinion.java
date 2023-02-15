package com.example.ProyectoEOI.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "opinion")
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comentario;
    private double puntuacion;
    private LocalDate fechaPuntuacion;

    @ManyToOne
    @JoinColumn (name="usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn (name="excursion_id")
    private Excursion excursion;










}
