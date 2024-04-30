package com.example.tfgfontanet.data.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "profesionales", schema = "samuelsanchez_tfg")
public class ProfesionalEntity {

    @Id
    @Column(name = "profesional_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer profesionalId;

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
    @JoinColumn(name = "user_id_p", referencedColumnName = "user_id")
    private UsuarioEntity usuarioProf;
}
