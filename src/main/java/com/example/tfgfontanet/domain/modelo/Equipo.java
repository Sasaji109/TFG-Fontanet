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
public class Equipo {
    private Integer id;
    private String nombre;
    private List<Jugador> jugadores;
}