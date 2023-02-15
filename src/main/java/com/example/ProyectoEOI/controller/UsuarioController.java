package com.example.ProyectoEOI.controller;

import com.example.ProyectoEOI.dto.LoginDto;
import com.example.ProyectoEOI.dto.UsuarioDTO;
import com.example.ProyectoEOI.exceptions.UsuarioException;
import com.example.ProyectoEOI.model.Reserva;
import com.example.ProyectoEOI.model.Usuario;
import com.example.ProyectoEOI.service.ReservaService;
import com.example.ProyectoEOI.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
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
        return "index";
        /*if (usuarioService.repository.repValidarPassword(usr,password) > 0)
        {
            return "index";
        }else {
            return "login";
        }*/
    }

    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(  WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage() ;
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
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
