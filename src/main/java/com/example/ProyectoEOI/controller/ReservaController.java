package com.example.ProyectoEOI.controller;

import com.example.ProyectoEOI.dto.ExcursionDTO;
import com.example.ProyectoEOI.dto.ReservaDTO;
import com.example.ProyectoEOI.dto.UsuarioDTO;
import com.example.ProyectoEOI.exceptions.ExcursionException;
import com.example.ProyectoEOI.exceptions.ReservaException;
import com.example.ProyectoEOI.exceptions.UsuarioException;
import com.example.ProyectoEOI.service.ExcursionService;
import com.example.ProyectoEOI.service.ReservaService;
import com.example.ProyectoEOI.service.UsuarioService;
import com.example.ProyectoEOI.util.ListarProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ReservaController {
    @ModelAttribute("reservas")
    public ListarProducto listarProducto(){
        return new ListarProducto();
    }

    // TODO: Añadir el control de errores en pagina

    private  final ExcursionService excSrvc;
    private final ReservaService service;
    private final UsuarioService usuarioService;

    @Autowired
    public ReservaController(ReservaService service, UsuarioService usuarioService, ExcursionService excSrvc) {
        this.service = service;
        this.usuarioService = usuarioService;
        this.excSrvc = excSrvc;
    }
    @GetMapping("/reserva/lista")
    public String verReservasCliente(
            @RequestParam("page")Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            ModelMap interfaz, RedirectAttributes attributes) throws UsuarioException {

        //Vamos a introducir el usuario en la sesión
        Long userId = 0L;
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()){
            usuarioDTO =  usuarioService.buscarUsuarioPorEmail(authentication.getName());
            userId = usuarioDTO.getId();
        }



        Integer pagina = page.map(integer -> integer - 1).orElse(0);
        Integer elementos = size.orElse(10);
        UsuarioDTO usuario = usuarioService.buscarUsuarioPorId(userId);
        Page<ReservaDTO> reserva = this.service.buscarReservaUsuario(usuario, PageRequest.of(pagina, elementos));

        interfaz.addAttribute("pageNumber", numeroPaginas(reserva));
        interfaz.addAttribute("listaR", reserva);

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
        Page<ExcursionDTO> excursion = this.excSrvc.buscarExcursiones(PageRequest.of(pagina, elementos));

        interfaz.addAttribute("pageNumber", numeroPaginasExc(excursion));
        interfaz.addAttribute("lista", excursion);
        //Ofrecer opciones de detalles, Comprar
        return "excursion/lista";
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

    private List<Integer> numeroPaginasExc(Page<ExcursionDTO> exc) {
        List<Integer> pageNumbers = new ArrayList<>();
        int numPaginas = exc.getTotalPages();
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

    @GetMapping(value = "/reserva/contratar")
    public String crearReserva(@RequestParam("id") Optional<Long> id) throws ExcursionException, UsuarioException {
        if (id.isPresent()) {
            ExcursionDTO exc = this.excSrvc.buscarExcursionPorId(id.get());
            ReservaDTO reserva = new ReservaDTO();
            reserva.setNombre(exc.getNombre());
            reserva.setIdExcursion(exc.getId());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            reserva.setIdUsuario(usuarioService.buscarUsuarioPorEmail(authentication.getName()).getId());
            reserva.setPrecio(exc.getPrecio());
            reserva.setPagada(false);
            ReservaDTO nuevaReserva = this.service.crearReserva(reserva);
            Long idReserva = nuevaReserva.getId();
            return "redirect:/reserva/%s".formatted(idReserva);
        } else {
            return "redirect:/reserva/lista";
        }

    }
    /*
    @PostMapping(value = "/reserva")
    public String crearReserva(ReservaDTO reserva) {
        ReservaDTO nuevaReserva = this.service.crearReserva(reserva);
        Long idReserva = reserva.getId();
        return "redirect:/reserva/%s".formatted(idReserva);

    }
*/
    @GetMapping(value = "/reserva/{id}")
    public String buscarResevaPorId(@PathVariable Long id, ModelMap interfaz) throws ReservaException {
        ReservaDTO reserva = this.service.buscarReservaPorId(id);
        interfaz.addAttribute("datosReserva", reserva);
        return "redirect:/reserva/contratar";
    }
    @PutMapping(value = "/reserva/{id}")
    public String modificarReserva (@PathVariable Long id, ReservaDTO reserva) throws ReservaException {
        reserva = this.service.modificarReserva(reserva);
        Long idReserva = reserva.getId();
        return "redirect:/reserva/%s".formatted(idReserva);
    }

    @PostMapping(value = "/reserva/cancelar/{id}")
    public String eliminarReserva(@PathVariable Long id) throws ReservaException{
        this.service.eliminarReserva(id);
        return "redirect:/reserva/lista";
    }

    @GetMapping("/reserva/pago/{id}")
    public String pagoreserva(@PathVariable Long id, Model mod) throws ReservaException {
        ReservaDTO res= this.service.buscarReservaPorId(id);
        res.setPagada(true);
        this.service.modificarReserva(res);
        mod.addAttribute("reserva", res);
        return "confirmacionReserva";
    }
}


