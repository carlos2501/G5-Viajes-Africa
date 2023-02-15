package com.example.ProyectoEOI.controller;

import com.example.ProyectoEOI.dto.LoginDto;
import com.example.ProyectoEOI.dto.UsuarioDTO;
import com.example.ProyectoEOI.exceptions.UsuarioException;
import com.example.ProyectoEOI.model.Reserva;
import com.example.ProyectoEOI.model.Usuario;
import com.example.ProyectoEOI.service.ReservaService;
import com.example.ProyectoEOI.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UsuarioController {

    // TODO: Añadir el control de errores en pagina

    private final UsuarioService usuarioService;

    private final ReservaService reservaService;

    public UsuarioController(UsuarioService usuarioService, ReservaService reservaService) {
        this.usuarioService = usuarioService;
        this.reservaService = reservaService;
    }


    //Controlador de Login
    @GetMapping("/login")
    public String vistaLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String logingUsusario(@ModelAttribute(name = "loginForm" ) LoginDto loginDto, Model model) throws UsuarioException {
        String errorMessage ="";
        String usr = loginDto.getUsername();
        System.out.println("usr :" + usr);
        String password = loginDto.getPassword();
        System.out.println("pass :" + password);
        try {
            UsuarioDTO usuario = usuarioService.login(usr, password);
            return String.format("redirect:/reserva/lista/%s", usuario.getId());
        } catch(Exception ex){
            errorMessage = ex.getMessage() ;
            model.addAttribute("errorMessage", errorMessage);
            return "login";
        }
    }


    @GetMapping("/registro")
    public String registroUsuario(ModelMap interfaz) {
        UsuarioDTO usuarioDto = new UsuarioDTO();
        interfaz.addAttribute("datosUsuario", usuarioDto);
        return "registro";
    }

    @PostMapping(value = "/registro")
    public String crearUsuario(UsuarioDTO usuario) throws UsuarioException {
        UsuarioDTO nuevoUsuario = this.usuarioService.crearUsuario(usuario);
        Long id = nuevoUsuario.getId();
        return "redirect:/usuario/%s".formatted(id);
    }

    @GetMapping(value="/usuario/{id}")
    public String verUsuario(@PathVariable Long id, ModelMap interfaz) throws UsuarioException {
        UsuarioDTO usuario = this.usuarioService.buscarUsuarioPorId(id);
        List<Reserva> reservaList = reservaService.buscarReservaUsuarioPerfil(id);
        interfaz.addAttribute("datosUsuario", usuario);
        interfaz.addAttribute("reservas", reservaList);
        return "perfil_usuario";
    }
    @PostMapping(value = "/usuario/{id}")
    public String modificarUsuario (@PathVariable Long id, UsuarioDTO usuario) throws UsuarioException {
        if (usuario.getPassword().isEmpty()){
            //Recuperar la anterior
            UsuarioDTO usuarioRec = this.usuarioService.buscarUsuarioPorId(id);
            usuario.setPassword(usuarioRec.getPassword());
        }

        UsuarioDTO usuarioDTOGuardar = this.usuarioService.modificarUsuario(usuario, id);

        //!!!!!!!
        Long idUsuario = usuarioDTOGuardar.getId();
        return "redirect:/usuario/%s".formatted(idUsuario);
    }
}
