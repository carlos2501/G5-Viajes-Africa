package com.example.ProyectoEOI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaViaje;
    private double precio;
    private LocalDate fechaInsert;
    private LocalDate fechaAct;
    private boolean pagada;
    private boolean activo;
    private Long idUsuario;
}
