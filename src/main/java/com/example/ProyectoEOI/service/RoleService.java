package com.example.ProyectoEOI.service;

import com.example.ProyectoEOI.dto.RoleDTO;
import com.example.ProyectoEOI.model.Role;
import com.example.ProyectoEOI.repository.RoleRepository;
import com.example.ProyectoEOI.repository.UsuarioRepository;
import com.example.ProyectoEOI.service.mapper.RoleServiceMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractBusinessService<Role, Integer, RoleDTO, RoleRepository, RoleServiceMapper> {

    private final UsuarioRepository usuarioRepository;

    protected RoleService(RoleRepository repository, RoleServiceMapper serviceMapper, UsuarioRepository usuarioRepository) {
        super(repository, serviceMapper);
        this.usuarioRepository = usuarioRepository;
    }

}
