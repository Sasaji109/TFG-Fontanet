package com.example.tfgfontanet.domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacturaMaterial {
    private Integer id;
    private Integer facturaId;
    private Material material;
    private Integer cantidad;
}

