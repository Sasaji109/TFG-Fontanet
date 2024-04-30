package com.example.tfgfontanet.data.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "clientes", schema = "samuelsanchez_tfg")
public class ClienteEntity {

    @Id
    @Column(name = "cliente_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "numero")
    private String numero;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id_c", referencedColumnName = "user_id")
    private UsuarioEntity usuarioClient;

    @OneToMany(mappedBy = "clienteId", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FavoritosEntity> profesionalesFavoritos;
}
