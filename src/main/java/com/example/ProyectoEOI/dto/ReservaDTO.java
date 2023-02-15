package com.example.ProyectoEOI.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private Long id;
    private String nombre;
    private LocalDate fechaViaje;
    private double precio;
    private LocalDate fechaInsert;
    private LocalDate fechaAct;
    private boolean pagada;
    private boolean activo;
    private Long idUsuario;
    private Long idExcursion;
}
