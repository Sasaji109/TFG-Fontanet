package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.domain.modelo.Cliente;
import com.example.tfgfontanet.domain.modelo.Contrato;
import com.example.tfgfontanet.domain.servicios.ContratosService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContratosController {

    private final ContratosService contratosService;

    @QueryMapping
    public List<Contrato> getAllContratos() {
        return contratosService.getAll().getOrElseThrow(() -> new NotFoundException("Contratos no encontrados"));
    }

    @QueryMapping
    public List<Contrato> getContratosByCliente(@Argument int clienteId) {
        return contratosService.getContratosByCliente(clienteId).getOrElseThrow(() -> new NotFoundException("Contratos no encontrados para el cliente"));
    }

    @QueryMapping
    public List<Contrato> getContratosByProfesional(@Argument int profesionalId) {
        return contratosService.getContratosByProfesional(profesionalId).getOrElseThrow(() -> new NotFoundException("Contratos no encontrados para el profesional"));
    }

    @QueryMapping
    public List<Contrato> getContratosByEstado(@Argument String estado) {
        return contratosService.getContratosByEstado(estado).getOrElseThrow(() -> new NotFoundException("Contratos no encontrados para el estado especificado"));
    }

    @QueryMapping
    public Contrato getContratoById(@Argument Integer contratoId) {
        return contratosService.get(contratoId).getOrElseThrow(() -> new NotFoundException("Contrato no encontrado"));
    }

    @PostMapping("/contrato/add")
    public String addContrato(@RequestBody Contrato contrato) {
        if (Boolean.TRUE.equals(contratosService.add(contrato))) {
            return "Contrato añadido exitosamente";
        } else {
            return "Contrato no añadido";
        }
    }

    @PutMapping("/contrato/update")
    public Integer updateContrato(@RequestBody Contrato contrato) {
        return contratosService.update(contrato).getOrElseThrow(() -> new CRUDException("Contrato no actualizado"));
    }

    @PutMapping("/contrato/updateEstado")
    public Integer updateContratoEstado(@RequestParam("contratoId") Integer contratoId, @RequestParam("estado") String estado) {
        return contratosService.updateEstado(contratoId, estado).getOrElseThrow(() -> new CRUDException("Estado del contrato no actualizado"));
    }
}
