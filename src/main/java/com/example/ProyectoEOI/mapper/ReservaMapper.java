package com.example.ProyectoEOI.mapper;

import com.example.ProyectoEOI.dto.ReservaDTO;
import com.example.ProyectoEOI.model.Reserva;
import com.example.ProyectoEOI.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper extends AbstractMapper<Reserva, ReservaDTO> {

    @Override
    public ReservaDTO entityToDto(Reserva reserva) {
        return ReservaDTO.builder()
                .id(reserva.getId())
                .fechaViaje(reserva.getFechaViaje())
                .precio(reserva.getPrecio())
                .pagada(reserva.isPagada())
                .fechaInsert(reserva.getFechaInsert())
                .fechaAct(reserva.getFechaAct())
                .idUsuario(reserva.getUsuario().getId())
                .activo(reserva.isActivo())
                .build();
    }

    @Override
    public Reserva dtoToEntity(ReservaDTO reservaDTO) {
        return Reserva.builder()
                .id(reservaDTO.getId())
                .fechaViaje(reservaDTO.getFechaViaje())
                .precio(reservaDTO.getPrecio())
                .pagada(reservaDTO.isPagada())
                .fechaInsert(reservaDTO.getFechaInsert())
                .fechaAct(reservaDTO.getFechaAct())
                .usuario(Usuario.builder().id(reservaDTO.getIdUsuario()).build())
                .activo(reservaDTO.isActivo())
                .build();
    }
}
