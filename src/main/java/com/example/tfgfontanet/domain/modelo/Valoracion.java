package com.example.tfgfontanet.domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Valoracion {
    private String nombreCliente;
    private String apellidoCliente;
    private Integer estrellas;
    private String descripcion;
}

