package com.example.tfgfontanet.data.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "servicios", schema = "example_exam_2eva")
public class ServicioEntity {

    @Id
    @Column(name = "servicioId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long servicioId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tarifaBase")
    private Double tarifaBase;
}
