package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.Cliente;
import com.example.tfgfontanet.domain.servicios.ClientesService;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import jakarta.annotation.security.RolesAllowed;
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
    @RolesAllowed({Constantes.ADMIN})
    public List<Cliente> getAllClientes() {
        return clientesService.getAll().getOrElseThrow(() -> new NotFoundException(Constantes.CLIENTES_NOT_FOUND));
    }

    @QueryMapping
    @RolesAllowed({Constantes.CLIENTE})
    public Cliente getClienteById(@Argument Integer clienteId) {
        return clientesService.getCliente(clienteId).getOrElseThrow(() -> new NotFoundException(Constantes.CLIENTE_NOT_FOUND));
    }

    @QueryMapping
    @RolesAllowed({Constantes.CLIENTE})
    public Cliente getClienteByUserId() {
        return clientesService.getByUserId().getOrElseThrow(() -> new NotFoundException(Constantes.CLIENTE_NOT_FOUND));
    }

    @MutationMapping
    @RolesAllowed({Constantes.ADMIN, Constantes.CLIENTE})
    public Integer deleteCliente(@Argument Integer clienteId) {
        return clientesService.delete(clienteId).getOrElseThrow(() -> new CRUDException(Constantes.CLIENTE_NO_ELIMINADO));
    }

    @PutMapping(Constantes.CLIENTE_UPDATE_PATH)
    @RolesAllowed({Constantes.CLIENTE})
    public Integer updateCliente(@RequestBody Cliente cliente) {
        return clientesService.update(cliente).getOrElseThrow(() -> new CRUDException(Constantes.CLIENTE_NO_ACTUALIZADO));
    }
}
