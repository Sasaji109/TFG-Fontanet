package com.example.tfgfontanet.domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contrato {
    private Integer contratoId;
    private Cliente cliente;
    private Profesional profesional;
    private Servicio servicio;
    private String fechaInicio;
    private String fechaFin;
    private String estado;
}

