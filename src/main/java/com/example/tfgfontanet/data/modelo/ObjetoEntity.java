package com.example.tfgfontanet.data.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "objetos", schema = "samuelsanchez_tfg")
public class ObjetoEntity {

    @Id
    @Column(name = "objetoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long objetoId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Double precio;
}
