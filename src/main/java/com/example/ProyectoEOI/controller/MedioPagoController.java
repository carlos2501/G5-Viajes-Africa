package com.example.ProyectoEOI.controller;

import com.example.ProyectoEOI.dto.MedioPagoDTO;
import com.example.ProyectoEOI.dto.PagoDTO;
import com.example.ProyectoEOI.dto.ReservaDTO;
import com.example.ProyectoEOI.exceptions.MedioPagoException;
import com.example.ProyectoEOI.exceptions.ReservaException;
import com.example.ProyectoEOI.service.MedioPagoService;
import com.example.ProyectoEOI.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedioPagoController {

    private final MedioPagoService service;

    @Autowired
    public MedioPagoController(MedioPagoService service) {
        this.service = service;
    }

    @GetMapping("/medioPago")
    public String medioPagos(ModelMap interfaz) {
        MedioPagoDTO medioPagoDto = new MedioPagoDTO();
        interfaz.addAttribute("tarjeta", medioPagoDto);
        return "medioPago";
    }
}

