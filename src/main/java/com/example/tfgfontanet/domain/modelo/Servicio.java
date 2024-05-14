package com.example.tfgfontanet.domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Servicio {
    private Integer servicioId;
    private String nombre;
    private String descripcion;
    private Double tarifaBase;
}

