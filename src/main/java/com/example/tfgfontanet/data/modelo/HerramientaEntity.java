package com.example.tfgfontanet.data.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "herramientas", schema = "samuelsanchez_tfg")
public class HerramientaEntity {

    @Id
    @Column(name = "herramientaId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long herramientaId;

    @Column(name = "nombre")
    private String nombre;
}
