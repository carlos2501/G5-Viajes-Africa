package com.example.ProyectoEOI.controller;

import com.example.ProyectoEOI.dto.ReservaDTO;
import com.example.ProyectoEOI.dto.UsuarioDTO;
import com.example.ProyectoEOI.exceptions.ReservaException;
import com.example.ProyectoEOI.exceptions.UsuarioException;
import com.example.ProyectoEOI.service.ReservaService;
import com.example.ProyectoEOI.service.UsuarioService;
import com.example.ProyectoEOI.util.ListarProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
@SessionAttributes("reservas")
@Controller
public class ReservaController {
    @ModelAttribute("reservas")
    public ListarProducto listarProducto(){
        return new ListarProducto();
    }

    // TODO: Añadir el control de errores en pagina

    private final ReservaService service;
    private final UsuarioService usuarioService;

    @Autowired
    public ReservaController(ReservaService service, UsuarioService usuarioService) {
        this.service = service;
        this.usuarioService = usuarioService;
    }
    @GetMapping("/reserva/lista/{id}")
    public String verReservasCliente(
            @PathVariable("id")Long id,
            @RequestParam("page")Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            ModelMap interfaz, RedirectAttributes attributes) throws UsuarioException {

        //Vamos a introducir el usuario en la sesión
        ListarProducto listarProducto = new ListarProducto();
        listarProducto.setIdUsuario(id);
        attributes.addFlashAttribute("reservas",listarProducto);



        Integer pagina = page.map(integer -> integer - 1).orElse(0);
        Integer elementos = size.orElse(10);
        UsuarioDTO usuario = usuarioService.buscarUsuarioPorId(id);
        Page<ReservaDTO> reserva = this.service.buscarReservaUsuario(usuario, PageRequest.of(pagina, elementos));

        interfaz.addAttribute("pageNumber", numeroPaginas(reserva));
        interfaz.addAttribute("idusuario",id);
        interfaz.addAttribute("lista", reserva);

        //Ofrecer opciones de detalles, Cancelar
        return "reserva/listareservasusuario";
    }
    @GetMapping("/reserva/crear")
    public String verReservas(
            @RequestParam("page")Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            ModelMap interfaz, RedirectAttributes attributes) {

        Integer pagina = page.map(integer -> integer - 1).orElse(0);
        Integer elementos = size.orElse(10);
        Page<ReservaDTO> reserva = this.service.buscarReservas(PageRequest.of(pagina, elementos));

        interfaz.addAttribute("pageNumber", numeroPaginas(reserva));
        interfaz.addAttribute("lista", reserva);
        //Ofrecer opciones de detalles, Comprar
        return "reserva/lista";
    }

    private List<Integer> numeroPaginas(Page<ReservaDTO> reserva) {
        List<Integer> pageNumbers = new ArrayList<>();
        int numPaginas = reserva.getTotalPages();
        if (numPaginas > 0) {
            pageNumbers = IntStream.rangeClosed(1, numPaginas)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return pageNumbers;
    }


    @GetMapping(value = "/reserva")
    public String nuevaReserva(ModelMap interfaz) {
        ReservaDTO nuevaReserva = new ReservaDTO();
        interfaz.addAttribute("datosReserva", nuevaReserva);
        return "pasarelaDePago";
    }

    @PostMapping(value = "/reserva")
    public String crearReserva(ReservaDTO reserva) {
        ReservaDTO nuevaReserva = this.service.crearReserva(reserva);
        Long idReserva = reserva.getId();
        return "redirect:/reserva/%s".formatted(idReserva);

    }

    @GetMapping(value = "/reserva/{id}")
    public String buscarResevaPorId(@PathVariable Long id, ModelMap interfaz) throws ReservaException {
        ReservaDTO reserva = this.service.buscarReservaPorId(id);
        interfaz.addAttribute("datosReserva", reserva);
        return "reserva/detalle";
    }
    @PutMapping(value = "/reserva/{id}")
    public String modificarReserva (@PathVariable Long id, ReservaDTO reserva) throws ReservaException {
        reserva = this.service.modificarReserva(reserva, id);
        Long idReserva = reserva.getId();
        return "redirect:/reserva/%s".formatted(idReserva);
    }

    @DeleteMapping(value = "/reserva/{id}")
    public String eliminarReserva(@PathVariable Long id) throws ReservaException{
        this.service.eliminarReserva(id);
        return "redirect:/reserva";
    }

}


