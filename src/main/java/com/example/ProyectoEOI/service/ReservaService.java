package com.example.ProyectoEOI.service;

import com.example.ProyectoEOI.dto.ReservaDTO;
import com.example.ProyectoEOI.dto.UsuarioDTO;
import com.example.ProyectoEOI.exceptions.ReservaException;
import com.example.ProyectoEOI.mapper.ReservaMapper;
import com.example.ProyectoEOI.model.Reserva;
import com.example.ProyectoEOI.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository repository;
    private final ReservaMapper mapper;

    @Autowired
    public ReservaService(ReservaRepository repository, ReservaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<ReservaDTO> buscarReservaUsuario(UsuarioDTO usuarioDTO, Pageable pageable) {
        return this.repository.findByUsuarioId(usuarioDTO.getId(), pageable).map(this.mapper::entityToDto);
    }

    public List<Reserva> buscarReservaUsuarioPerfil(Long id) {
        return this.repository.findByUsuarioId(id);
    }
    public Page<ReservaDTO> buscarReservas (Pageable pageable) {
        return this.repository.findAll(pageable).map(this.mapper::entityToDto);}


    public ReservaDTO crearReserva (ReservaDTO reservaDto) {
        Reserva reserva = this.mapper.dtoToEntity(reservaDto);
        reserva = this.repository.save(reserva);
        return this.mapper.entityToDto(reserva);

    }

    public ReservaDTO buscarReservaPorId(Long id) throws ReservaException {
        Reserva reserva = buscarReserva(id);
        return this.mapper.entityToDto(reserva);
    }

    public ReservaDTO modificarReserva(ReservaDTO reservaDTO) throws ReservaException {
        this.repository.save(mapper.dtoToEntity(reservaDTO));
        return reservaDTO;
    }
    public void eliminarReserva(Long id) throws ReservaException {
        Reserva reserva = buscarReserva(id);
        this.repository.delete(reserva);
    }

    private Reserva buscarReserva(Long id) throws ReservaException {
        Optional<Reserva> optionalReserva = this.repository.findById(id);

        if (optionalReserva.isEmpty()) {
            throw new ReservaException("No se ha encontrado la reserva con el id %d".formatted(id));
        }
        return optionalReserva.get();
    }
}




