package com.example.tfgfontanet.data.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "facturas", schema = "samuelsanchez_tfg")
public class FacturaEntity {

    @Id
    @Column(name = "factura_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer facturaId;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cliente_id_f", referencedColumnName = "cliente_id")
    private ClienteEntity cliente;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "profesional_id_f", referencedColumnName = "profesional_id")
    private ProfesionalEntity profesional;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "servicio_id_f", referencedColumnName = "servicio_id")
    private ServicioEntity servicio;

    @OneToMany(mappedBy = "facturaId", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FacturaMaterialEntity> materiales;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "estado")
    private String estado;
}
