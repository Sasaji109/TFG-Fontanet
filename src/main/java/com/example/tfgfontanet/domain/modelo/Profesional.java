package com.example.tfgfontanet.domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profesional {
    private Integer profesionalId;
    private String nombre;
    private String apellidos;
    private String numero;
    private Integer experiencia;
    private String oficio;
    private String disponibilidad;
    private Integer valoracion;
    private Usuario usuario;
}

