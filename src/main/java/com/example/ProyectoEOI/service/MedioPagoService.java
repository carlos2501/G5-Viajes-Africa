package com.example.ProyectoEOI.service;

import com.example.ProyectoEOI.dto.MedioPagoDTO;
import com.example.ProyectoEOI.dto.UsuarioDTO;
import com.example.ProyectoEOI.mapper.MedioPagoMapper;
import com.example.ProyectoEOI.mapper.PagoMapper;
import com.example.ProyectoEOI.model.MedioPago;
import com.example.ProyectoEOI.model.Usuario;
import com.example.ProyectoEOI.repository.MedioPagoRepository;
import com.example.ProyectoEOI.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedioPagoService {

    private final MedioPagoRepository repository;
    private final MedioPagoMapper mapper;

    @Autowired
    public MedioPagoService(MedioPagoRepository repository, MedioPagoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<MedioPagoDTO> buscarTodosMedioPago() {
        List<MedioPago> medioPagos = this.repository.findAll();
        return this.mapper.entitiesToDtos(medioPagos);
    }

}
