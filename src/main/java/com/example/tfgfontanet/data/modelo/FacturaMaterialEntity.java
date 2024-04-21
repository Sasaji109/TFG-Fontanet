package com.example.tfgfontanet.data.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "factura_material", schema = "samuelsanchez_tfg")
public class FacturaMaterialEntity {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "facturaId")
    private Integer facturaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materialId")
    private MaterialEntity material;

    @Column(name = "cantidad")
    private Integer cantidad;
}
