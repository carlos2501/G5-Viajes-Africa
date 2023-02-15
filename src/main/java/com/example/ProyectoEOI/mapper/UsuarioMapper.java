package com.example.ProyectoEOI.mapper;

import com.example.ProyectoEOI.dto.UsuarioDTO;
import com.example.ProyectoEOI.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper extends AbstractMapper<Usuario, UsuarioDTO> {

    @Override
    public UsuarioDTO entityToDto(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .telefono(usuario.getTelefono())
                .email(usuario.getEmail())
                .fechaInsert(usuario.getFechaInsert())
                .fechaAct(usuario.getFechaAct())
                .password(usuario.getPassword())
                .build();
    }

    @Override
    public Usuario dtoToEntity(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .id(usuarioDTO.getId())
                .nombre(usuarioDTO.getNombre())
                .apellidos(usuarioDTO.getApellidos())
                .fechaNacimiento(usuarioDTO.getFechaNacimiento())
                .telefono(usuarioDTO.getTelefono())
                .email(usuarioDTO.getEmail())
                .fechaInsert(usuarioDTO.getFechaInsert())
                .fechaAct(usuarioDTO.getFechaAct())
                .password(usuarioDTO.getPassword())
                .build();
    }
}
