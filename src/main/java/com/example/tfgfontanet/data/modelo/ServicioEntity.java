package com.example.tfgfontanet.data.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "servicios", schema = "samuelsanchez_tfg")
public class ServicioEntity {

    @Id
    @Column(name = "servicioId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer servicioId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tarifaBase")
    private Double tarifaBase;
}
