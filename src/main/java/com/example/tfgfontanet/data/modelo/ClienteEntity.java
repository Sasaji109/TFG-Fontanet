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
@Table(name = "clientes", schema = "example_exam_2eva")
public class ClienteEntity {

    @Id
    @Column(name = "clienteId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "numero")
    private String numero;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "clienteId", referencedColumnName = "userId")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "clienteId", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ProfesionalEntity> profesionalesFavoritos;
}
