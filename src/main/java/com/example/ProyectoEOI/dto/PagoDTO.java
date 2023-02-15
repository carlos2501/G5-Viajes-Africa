package com.example.ProyectoEOI.dto;

import com.example.ProyectoEOI.model.MedioPago;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {
    private Long id;
    private LocalDate fecha;
    private double precio;
    private Long idMedioPago;
    private Long idReserva;
}
