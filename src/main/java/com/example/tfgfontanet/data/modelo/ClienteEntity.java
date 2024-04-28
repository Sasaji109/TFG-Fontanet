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
@Table(name = "clientes", schema = "samuelsanchez_tfg")
public class ClienteEntity {

    @Id
    @Column(name = "clienteId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "numero")
    private String numero;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "clienteId", referencedColumnName = "userId")
    private UsuarioEntity usuarioClient;

    @OneToMany(mappedBy = "profesionalId", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ProfesionalEntity> profesionalesFavoritos;
}
