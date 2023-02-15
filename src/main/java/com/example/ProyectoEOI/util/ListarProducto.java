package com.example.ProyectoEOI.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.TrueFalseConverter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListarProducto {

    private Long idUsuario;
    private Long idReserva;
    private Long idPago;

    public boolean CompraIniciada(){
        if (idUsuario > 0)
            return true;
        else
            return false;
    }
}
