package com.example.ProyectoEOI.controller;

import com.example.ProyectoEOI.dto.PagoDTO;
import com.example.ProyectoEOI.dto.UsuarioDTO;
import com.example.ProyectoEOI.service.PagoService;
import com.example.ProyectoEOI.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagoController {
    private final PagoService service;

    public PagoController(PagoService service) {
        this.service = service;
    }

    @GetMapping("/precio")
    public String importe(ModelMap interfaz){
        PagoDTO pagoDto = new PagoDTO();
        interfaz.addAttribute("precio", pagoDto);
        return "precio";
    }

}

