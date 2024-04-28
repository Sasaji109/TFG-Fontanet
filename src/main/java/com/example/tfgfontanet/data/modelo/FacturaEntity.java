package com.example.tfgfontanet.data.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "facturas", schema = "samuelsanchez_tfg")
public class FacturaEntity {

    @Id
    @Column(name = "facturaId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer facturaId;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "facturaId", referencedColumnName = "clienteId")
    private ClienteEntity cliente;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "facturaId", referencedColumnName = "profesionalId")
    private ProfesionalEntity profesional;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "facturaId", referencedColumnName = "servicioId")
    private ServicioEntity servicio;

    @OneToMany(mappedBy = "facturaId", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FacturaMaterialEntity> materiales;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "estado")
    private String estado;
}
