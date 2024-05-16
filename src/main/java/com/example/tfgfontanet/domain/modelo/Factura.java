package com.example.tfgfontanet.domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Factura {
    private Integer facturaId;
    private Cliente cliente;
    private Profesional profesional;
    private Servicio servicio;
    private List<FacturaMaterial> materiales;
    private Double precio;
    private String estado;
}

