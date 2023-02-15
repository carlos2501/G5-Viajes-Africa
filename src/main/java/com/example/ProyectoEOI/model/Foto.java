package com.example.ProyectoEOI.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.core.SpringVersion;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "foto")
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private LocalDate fecha;
    private String url;

    @ColumnDefault("true")
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "foto_id")
    private Excursion excursion;


}
