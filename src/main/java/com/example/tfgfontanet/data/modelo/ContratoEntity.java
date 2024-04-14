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
@Table(name = "contratos", schema = "example_exam_2eva")
public class ContratoEntity {

    @Id
    @Column(name = "contratoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contratoId;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "contratoId", referencedColumnName = "clienteId")
    private ClienteEntity cliente;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "contratoId", referencedColumnName = "profesionalId")
    private ProfesionalEntity profesional;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "contratoId", referencedColumnName = "servicioId")
    private ServicioEntity servicio;

    @Column(name = "fechaInicio")
    private LocalDate fechaInicio;

    @Column(name = "fechaFin")
    private LocalDate fechaFin;

    @Column(name = "estado")
    private String estado;
}
