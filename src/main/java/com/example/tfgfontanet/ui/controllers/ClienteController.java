package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.domain.modelo.Cliente;
import com.example.tfgfontanet.domain.servicios.ClientesService;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClienteController {

    private final ClientesService clientesService;

    @QueryMapping
    public List<Cliente> getAllClientes() {
        return clientesService.getAll().getOrElseThrow(() -> new NotFoundException("Clientes no encontrados"));
    }

    @QueryMapping
    public Cliente getClienteById(@Argument Integer clienteId) {
        return clientesService.get(clienteId).getOrElseThrow(() -> new NotFoundException("Cliente no encontrado"));
    }

    @MutationMapping
    public Integer deleteCliente(@Argument Integer clienteId) {
        return clientesService.delete(clienteId).getOrElseThrow(() -> new CRUDException("Cliente no eliminado"));
    }

    @PutMapping("/cliente/update")
    public Integer updateCliente(@RequestBody Cliente cliente) {
        return clientesService.update(cliente).getOrElseThrow(() -> new CRUDException("Cliente no actualizado"));
    }
}
