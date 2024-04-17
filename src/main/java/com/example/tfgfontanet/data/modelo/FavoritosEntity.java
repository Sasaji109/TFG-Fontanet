package com.example.tfgfontanet.data.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "favoritos", schema = "samuelsanchez_tfg")
public class FavoritosEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "clienteId")
    private Integer clienteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesionalId")
    private ProfesionalEntity profesional;
}
