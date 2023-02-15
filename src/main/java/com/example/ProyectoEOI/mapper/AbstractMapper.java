package com.example.ProyectoEOI.mapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractMapper<ENTITY, DTO> {

    public abstract DTO entityToDto(ENTITY entity);

    public abstract ENTITY dtoToEntity(DTO dto);

    public List<ENTITY> dtosToEntities(List<DTO> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public List<DTO> entitiesToDtos(List<ENTITY> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
