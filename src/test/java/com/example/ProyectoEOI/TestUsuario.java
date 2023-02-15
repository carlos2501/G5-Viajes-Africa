package com.example.ProyectoEOI;

import com.example.ProyectoEOI.controller.UsuarioController;
import com.example.ProyectoEOI.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UsuarioController.class)
public class TestUsuario {

    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    /*@Test
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    void getUserByIdTest() throws Exception {
        Usuario usuario = Usuario.builder()
                .nombre("Jhon")
                .apellidos("Aristizabal")
                .direccion("Serrano 44")
                .dni("12345677A")
                .email("xxxxxxxx@xxxx.xxx")
                .password("123456789")
                .telefono("23456125")
                .codigoPostal("12365")
                .fechaNacimiento(LocalDate.of(1997,3,2))
                .suscriptorNewsletter(false)
                .build();

        when(usuarioService.buscarPorId(anyLong())).thenReturn(usuario);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Jhon"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.apellidos").value("Aristizabal"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.direccion").value("Serrano 44"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dni").value("12345677A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("xxxxxxxx@xxxx.xxx"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("123456789"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telefono").value("23456125"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.codigoPostal").value("12365"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fechaNacimiento").value("1997-03-02"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.suscriptorNewsletter").value(false))
                .andExpect(status().isOk());

    }*/

    /*
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioDTOToUsuario usuarioDTOToUsuario;
    //private UsuarioDTOToUsuario usuarioDTOToUsuario;

    @Test
    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    public void testGuardarUsuario() {
        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .nombre("Jhon")
                .apellidos("Aristizabal")
                .direccion("Serrano 44")
                .dni("12345677A")
                .email("xxxxxxxx@xxxx.xxx")
                .password("123456789")
                .telefono("23456125")
                .codigoPostal("12365")
                .fechaNacimiento(LocalDate.of(1997,3,2))
                .suscriptorNewsletter(false)
                .build();

        Usuario usuario = usuarioService.crearNuevoUsuario(usuarioDTO);
        assertNotNull(usuario);
        assertEquals("Jhon",usuarioService.buscarPorId(usuario.getId()).getNombre());
    }*/
}
