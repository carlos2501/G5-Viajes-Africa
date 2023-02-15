package com.example.ProyectoEOI.mapper;

import com.example.ProyectoEOI.dto.MedioPagoDTO;
import com.example.ProyectoEOI.dto.PagoDTO;
import com.example.ProyectoEOI.model.MedioPago;
import com.example.ProyectoEOI.model.Pago;
import com.example.ProyectoEOI.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class MedioPagoMapper extends AbstractMapper<MedioPago, MedioPagoDTO> {

    @Override
    public MedioPagoDTO entityToDto (MedioPago medioPago) {
        return MedioPagoDTO.builder()
                .id(medioPago.getId())
                .tarjeta(medioPago.getTarjeta())
                .numeroTarjeta(medioPago.getNumeroTarjeta())
                .cvv(medioPago.getCvv())
                .fechaValidez(medioPago.getFechaValidez())
                .idUsuario(medioPago.getUsuario().getId())
                .build();
    }

    @Override
    public MedioPago dtoToEntity (MedioPagoDTO medioPagoDTO) {
        return MedioPago.builder()
                .id(medioPagoDTO.getId())
                .tarjeta(medioPagoDTO.getTarjeta())
                .numeroTarjeta(medioPagoDTO.getNumeroTarjeta())
                .cvv(medioPagoDTO.getCvv())
                .fechaValidez(medioPagoDTO.getFechaValidez())
                .usuario(Usuario.builder().id(medioPagoDTO.getIdUsuario()).build())
                .build();


    }
}
