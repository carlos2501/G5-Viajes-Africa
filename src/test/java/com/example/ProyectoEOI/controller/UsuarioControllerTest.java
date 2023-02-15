package com.example.ProyectoEOI.controller;

import com.example.ProyectoEOI.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGuardarUsuario() throws Exception {
        /*
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

        Usuario usuario = new Usuario();

        when(usuarioService.crearNuevoUsuario(usuarioDTO)).thenReturn(usuario);
        mockMvc.perform(post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(usuario)))
                .andExpect(status().isOk()).andDo(print());
                */
        /*UsuarioDTO usuarioDTO = UsuarioDTO.builder()
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

        given(usuarioService.crearNuevoUsuario(ArgumentMatchers.any())).willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));


        ResultActions response = mockMvc.perform(post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioDTO)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());*/
    }
}
