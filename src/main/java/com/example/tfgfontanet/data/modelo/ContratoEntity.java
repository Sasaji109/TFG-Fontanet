package com.example.tfgfontanet.data.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "contratos", schema = "samuelsanchez_tfg")
public class ContratoEntity {

    @Id
    @Column(name = "contratoId")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contratoId;

    @Column(name = "clienteId")
    private Integer clienteId;

    @Column(name = "profesionalId")
    private Integer profesionalId;

    @Column(name = "servicioId")
    private Integer servicioId;
/*
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "contratoId", referencedColumnName = "clienteId")
    private ClienteEntity cliente;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "contratoId", referencedColumnName = "profesionalId")
    private ProfesionalEntity profesional;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "contratoId", referencedColumnName = "servicioId")
    private ServicioEntity servicio;
*/
    @Column(name = "fechaInicio")
    private LocalDate fechaInicio;

    @Column(name = "fechaFin")
    private LocalDate fechaFin;

    @Column(name = "estado")
    private String estado;
}
