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
@Table(name = "profesionales", schema = "example_exam_2eva")
public class ProfesionalEntity {

    @Id
    @Column(name = "profesionalId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profesionalId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "numero")
    private String numero;

    @Column(name = "experiencia")
    private Integer experiencia;

    @Column(name = "oficio")
    private String oficio;

    @Column(name = "disponibilidad")
    private String disponibilidad;

    @Column(name = "valoracion")
    private Integer valoracion;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "profesionalId", referencedColumnName = "userId")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "profesionalId", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<HerramientaEntity> herramientas;
}
