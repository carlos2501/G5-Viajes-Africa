package com.example.ProyectoEOI.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppMainController {

    @GetMapping ({"","/"})
    public String index (){
        return "index";
    }
}
