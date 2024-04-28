package com.example.tfgfontanet.data.modelo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ValoracionMongo {
    private String nombreCliente;
    private String apellidoCliente;
    private Integer estrellas;
    private String descripcion;
}
