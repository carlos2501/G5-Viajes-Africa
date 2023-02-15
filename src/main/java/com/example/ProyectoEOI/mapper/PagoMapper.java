package com.example.ProyectoEOI.mapper;

import com.example.ProyectoEOI.dto.PagoDTO;
import com.example.ProyectoEOI.dto.ReservaDTO;
import com.example.ProyectoEOI.model.MedioPago;
import com.example.ProyectoEOI.model.Pago;
import com.example.ProyectoEOI.model.Reserva;
import com.example.ProyectoEOI.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PagoMapper extends AbstractMapper<Pago, PagoDTO>  {
    @Override
    public PagoDTO entityToDto (Pago pago) {
        return PagoDTO.builder()
                .id(pago.getId())
                .precio(pago.getPrecio())
                .fecha(pago.getFecha())
                .idMedioPago(pago.getMedioPago().getId())
                .idReserva(pago.getReserva().getId())
                .build();
    }

    @Override
    public Pago dtoToEntity(PagoDTO pagoDTO) {
        return Pago.builder()
                .id(pagoDTO.getId())
                .precio(pagoDTO.getPrecio())
                .fecha(pagoDTO.getFecha())
                .medioPago(MedioPago.builder().id(pagoDTO.getIdMedioPago()).build())
                .reserva(Reserva.builder().id(pagoDTO.getIdReserva()).build())
                .build();

    }
}
