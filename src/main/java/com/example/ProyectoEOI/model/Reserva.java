package com.example.ProyectoEOI.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaViaje;
    private double precio;
    private LocalDate fechaInsert;
    private LocalDate fechaAct;
    private boolean pagada;
    @ColumnDefault("true")
    private boolean activo;

    //Relaciones
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne
    @JoinColumn (name = "excusion_id")
    private Excursion excursion;

    @OneToOne
    private Pago pago;

}










