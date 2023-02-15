package com.example.ProyectoEOI.service;

import com.example.ProyectoEOI.dto.PagoDTO;
import com.example.ProyectoEOI.dto.ReservaDTO;
import com.example.ProyectoEOI.dto.UsuarioDTO;
import com.example.ProyectoEOI.exceptions.PagoException;
import com.example.ProyectoEOI.exceptions.ReservaException;
import com.example.ProyectoEOI.exceptions.UsuarioException;
import com.example.ProyectoEOI.mapper.PagoMapper;
import com.example.ProyectoEOI.mapper.ReservaMapper;
import com.example.ProyectoEOI.model.Pago;
import com.example.ProyectoEOI.model.Reserva;
import com.example.ProyectoEOI.model.Usuario;
import com.example.ProyectoEOI.repository.PagoRepository;
import com.example.ProyectoEOI.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    private final PagoRepository repository;
    private final PagoMapper mapper;

    @Autowired
    public PagoService(PagoRepository repository, PagoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PagoDTO> buscarTodosPagos() {
        List<Pago> pagos = this.repository.findAll();
        return this.mapper.entitiesToDtos(pagos);
    }
    private Pago buscarPagos(double precio) {
        return null;
    }

    public PagoDTO buscarPrecio (double precio) throws PagoException {
        Pago pago = buscarPagos(precio);
        return this.mapper.entityToDto(pago);

    }


}
