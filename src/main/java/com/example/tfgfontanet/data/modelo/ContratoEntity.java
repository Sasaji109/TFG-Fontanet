package com.example.tfgfontanet.data.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "contratos", schema = "samuelsanchez_tfg")
public class ContratoEntity {

    @Id
    @Column(name = "contrato_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contratoId;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cliente_id_c", referencedColumnName = "cliente_id")
    private ClienteEntity cliente;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "profesional_id_c", referencedColumnName = "profesional_id")
    private ProfesionalEntity profesional;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "servicio_id_c", referencedColumnName = "servicio_id")
    private ServicioEntity servicio;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "estado")
    private String estado;
}
