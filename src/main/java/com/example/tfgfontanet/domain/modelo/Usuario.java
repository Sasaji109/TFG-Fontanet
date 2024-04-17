package com.example.tfgfontanet.domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    private int id;
    private String nombre;
    private String fechaNacimiento;
    private String password;
    private String role;
}
