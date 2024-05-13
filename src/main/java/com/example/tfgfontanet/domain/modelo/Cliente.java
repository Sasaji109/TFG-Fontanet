package com.example.tfgfontanet.domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {
    private Integer clienteId;
    private String nombre;
    private String apellidos;
    private String numero;
    private Usuario usuario;
}

