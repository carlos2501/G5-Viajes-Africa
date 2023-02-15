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

public class MedioPagoDTO {
    private Long id;
    private String tarjeta;
    private int numeroTarjeta;
    private int cvv;
    private LocalDate fechaValidez;
    private Long idUsuario;
}
