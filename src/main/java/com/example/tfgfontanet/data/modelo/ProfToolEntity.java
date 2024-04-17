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
@Table(name = "profesional_herramienta", schema = "samuelsanchez_tfg")
public class ProfToolEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "profesionalId")
    private Integer profesionalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "herramientaId")
    private HerramientaEntity herramienta;

    @Column(name = "cantidad")
    private Integer cantidad;
}
