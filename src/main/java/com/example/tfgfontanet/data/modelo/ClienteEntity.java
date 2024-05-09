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

    @OneToOne
    @JoinColumn(name = "user_id_c", referencedColumnName = "user_id")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "clienteId", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FavoritosEntity> profesionalesFavoritos;

    public ClienteEntity(Integer clienteId, String nombre, String apellidos, String numero, UsuarioEntity usuario) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numero = numero;
        this.usuario = usuario;
    }
}
