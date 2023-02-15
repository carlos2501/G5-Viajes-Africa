package com.example.ProyectoEOI.service;

import com.example.ProyectoEOI.model.Usuario;
import com.example.ProyectoEOI.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.ProyectoEOI.dto.UsuarioDTO;
import com.example.ProyectoEOI.exceptions.UsuarioException;
import com.example.ProyectoEOI.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class UsuarioService {

    public final UsuarioRepository repository;
    public final UsuarioMapper mapper;

    @Autowired
    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<UsuarioDTO> buscarTodosUsuarios() {
        List<Usuario> usuarios = this.repository.findAll();
        return this.mapper.entitiesToDtos(usuarios);
    }

    public UsuarioDTO buscarUsuarioPorId(Long id) throws UsuarioException {
        Usuario usuario = buscarUsuario(id);
        return this.mapper.entityToDto(usuario);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email) throws UsuarioException {
        Usuario usuario = repository.findUsuarioByEmail(email);
        return this.mapper.entityToDto(usuario);
    }

    /*public UsuarioDTO login(String email,String password) throws UsuarioException{
        Optional<Usuario> optionalUsuario = this.repository.findByEmail(email);
        System.out.println("Datos de entrada login: " +  email + ":" +password );
        if (optionalUsuario.isEmpty()) {
            throw new UsuarioException("No se ha encontrado ningun usuario con el email %s".formatted(email));
        }

        Usuario usuario = optionalUsuario.get();
        System.out.println("Datos de entrada **: " +  email + ":" +usuario.getPassword() );
        if (!usuario.getPassword().equals(password)) {
            throw new UsuarioException("La contraseña introducida no coincide");
        }
        return this.mapper.entityToDto(usuario);
    }
    */

    public UsuarioDTO guardar(UsuarioDTO usuarioDtoPsw){
        System.out.println("usuarioDto:" +usuarioDtoPsw.getNombre() );
        //Traduzco del dto con datos de entrada a la entidad
        final Usuario entidad = mapper.dtoToEntity(usuarioDtoPsw);
        System.out.println("Entidad:" +entidad.getNombre() );
        //Guardo el la base de datos
        Usuario entidadGuardada =  repository.save(entidad);
        //Traducir la entidad a DTO para devolver el DTO
        return mapper.entityToDto(entidadGuardada);
    }
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDto) throws UsuarioException {
        if (!usuarioDto.getPassword().equals(usuarioDto.getConfirmarPassword())) {
            throw new UsuarioException("Las contraseñas no coinciden");
        }

        Usuario usuario = this.mapper.dtoToEntity(usuarioDto);
        usuario = this.repository.save(usuario);
        return this.mapper.entityToDto(usuario);
    }

    public UsuarioDTO modificarUsuario (UsuarioDTO usuarioDTO, Long id) throws UsuarioException {
        Usuario usuario = buscarUsuario(id);
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(usuarioDTO.getPassword());

        usuario = this.repository.save(usuario);
        return this.mapper.entityToDto(usuario);
    }

    public void desactivarUsuario(Long id) throws UsuarioException {
        Usuario usuario = buscarUsuario(id);
        usuario.setActivo(false);
        this.repository.save(usuario);
    }

    public void borrarUsuario(Long id) throws Exception {
        Usuario usuario = buscarUsuario(id);
        this.repository.delete(usuario);
    }

    private Usuario buscarUsuario(Long id) throws UsuarioException {
        Optional<Usuario> optionalUsuario = this.repository.findById(id);

        if (optionalUsuario.isEmpty()) {
            throw new UsuarioException("No se ha encontrado ningun usuario con el id %d".formatted(id));
        }

        return optionalUsuario.get();
    }

    public Object getMapper() {
        return this.mapper;
    }
}
