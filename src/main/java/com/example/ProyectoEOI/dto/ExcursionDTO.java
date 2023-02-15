package com.example.ProyectoEOI.dto;

import com.example.ProyectoEOI.constants.Dificultad;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcursionDTO {

    private Long id;
    private String detalles;
    private String duracion;
    private String informacion;
    private String itinerario;
    private Integer maxPersonas;
    private String nombre;
    private Double precio;
}
