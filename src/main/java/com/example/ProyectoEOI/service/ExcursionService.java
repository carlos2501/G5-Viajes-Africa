package com.example.ProyectoEOI.service;

import com.example.ProyectoEOI.dto.ExcursionDTO;
import com.example.ProyectoEOI.exceptions.ExcursionException;
import com.example.ProyectoEOI.mapper.ExcursionMapper;
import com.example.ProyectoEOI.model.Excursion;
import com.example.ProyectoEOI.repository.ExcursionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExcursionService {

    private final ExcursionRepository repository;
    private final ExcursionMapper mapper;

    @Autowired
    public ExcursionService(ExcursionRepository repository, ExcursionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<ExcursionDTO>buscarExcursiones(Pageable pageable) {
        return this.repository.findAll(pageable).map(this.mapper::entityToDto);
    }

    public ExcursionDTO crearExcursion(ExcursionDTO excursionDto) {
        Excursion excursion = this.mapper.dtoToEntity(excursionDto);
        excursion = this.repository.save(excursion);
        return this.mapper.entityToDto(excursion);
    }

    public ExcursionDTO buscarExcursionPorId(Long id) throws ExcursionException {
        Excursion excursion = buscarExcursion(id);
        return  this.mapper.entityToDto(excursion);
    }

    public ExcursionDTO actualizarExcursion(ExcursionDTO excursionDTO, Long id) throws ExcursionException {
        Excursion excursion = buscarExcursion(id);
        excursion.setNombre(excursionDTO.getNombre());

        // Terminar de actualizar los datos de la excursion

        excursion = this.repository.save(excursion);
        return this.mapper.entityToDto(excursion);
    }

    public void eliminarExcursion(Long id) throws ExcursionException {
        Excursion excursion = buscarExcursion(id);
        this.repository.delete(excursion);
    }

    private Excursion buscarExcursion(Long id) throws ExcursionException {
        Optional<Excursion> optionalExcursion = this.repository.findById(id);

        if (optionalExcursion.isEmpty()) {
            throw new ExcursionException("No se ha encontrado la excursion con el id %d".formatted(id));
        }

        return optionalExcursion.get();
    }
}
