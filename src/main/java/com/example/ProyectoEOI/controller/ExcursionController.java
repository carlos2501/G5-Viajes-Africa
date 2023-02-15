package com.example.ProyectoEOI.controller;

import com.example.ProyectoEOI.dto.ExcursionDTO;
import com.example.ProyectoEOI.dto.UsuarioDTO;
import com.example.ProyectoEOI.exceptions.ExcursionException;
import com.example.ProyectoEOI.service.ExcursionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ExcursionController {

    // TODO: Añadir el control de errores en pagina

    private final ExcursionService service;

    @Autowired
    public ExcursionController(ExcursionService service) {
        this.service = service;
    }

    @GetMapping(value = "/excursion/listado")
    public String buscarTodasLasExcursiones(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            ModelMap interfaz) {

        Integer pagina = page.map(integer -> integer - 1).orElse(0);
        Integer elementos = size.orElse(10);
        Page<ExcursionDTO> excursiones = this.service.buscarExcursiones(PageRequest.of(pagina, elementos));
        interfaz.addAttribute("pageNumber", numeroPaginas(excursiones));
        interfaz.addAttribute("lista", excursiones);

        return "excursion/lista";
    }

    @GetMapping(value = "/excursion/{id}")
    public String buscarExcursionPorId(@PathVariable Long id, ModelMap interfaz) throws ExcursionException {
        ExcursionDTO excursion = this.service.buscarExcursionPorId(id);
        interfaz.addAttribute("datosExcursion", excursion);
        return "excursion/detalle";
    }

    @GetMapping(value = "/excursion")
    public String nuevaExcursion(ModelMap interfaz) {
        ExcursionDTO nuevaExcursion = new ExcursionDTO();
        interfaz.addAttribute("datosExcursion", nuevaExcursion);
        return "excursion/nueva";
    }

    @PostMapping(value = "/excursion")
    public String crearExcursion(ExcursionDTO excursion) {
        ExcursionDTO nuevaExcursion = this.service.crearExcursion(excursion);
        Long idExcursion = excursion.getId();
        return "redirect:/excursion/%s".formatted(idExcursion);

    }

    @PutMapping(value = "/excursion/{id}")
    public String actualizarExcursion(@PathVariable Long id, ExcursionDTO excursion) throws ExcursionException {
        excursion = this.service.actualizarExcursion(excursion, id);
        Long idExcursion = excursion.getId();
        return "redirect:/excursion/%s".formatted(idExcursion);
    }

    @DeleteMapping(value = "/excursion/{id}")
    public String eliminarExcurson(@PathVariable Long id) throws ExcursionException{
        this.service.eliminarExcursion(id);
        return "redirect:/excursion";
    }

    private List<Integer> numeroPaginas(Page<ExcursionDTO> excursiones) {
        List<Integer> pageNumbers = new ArrayList<>();
        int numPaginas = excursiones.getTotalPages();
        if (numPaginas > 0) {
            pageNumbers = IntStream.rangeClosed(1, numPaginas)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return pageNumbers;
    }
}
