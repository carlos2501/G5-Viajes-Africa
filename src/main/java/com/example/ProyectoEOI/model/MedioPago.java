package com.example.ProyectoEOI.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "medioPago")
public class MedioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tarjeta;
    private int numeroTarjeta;
    private int cvv;
    private LocalDate fechaValidez;

    @ManyToOne
    @JoinColumn (name="usuario_id")
    private Usuario usuario;

    @OneToOne
    private Pago pago;

}
