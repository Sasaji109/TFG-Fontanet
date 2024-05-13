package com.example.tfgfontanet.domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    private Integer userId;
    private String username;
    private String correo;
    private String password;
    private Boolean activado;
    private LocalDateTime fechaEnvio;
    private String codigoActivacion;
    private String role;
}
