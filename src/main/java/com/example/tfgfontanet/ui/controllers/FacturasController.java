package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.Factura;
import com.example.tfgfontanet.domain.servicios.FacturasService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FacturasController {

    private final FacturasService facturasService;

    @QueryMapping
    public List<Factura> getAllFacturas() {
        return facturasService.getAll().getOrElseThrow(() -> new NotFoundException("Facturas no encontradas"));
    }

    @QueryMapping
    @RolesAllowed({Constantes.CLIENTE})
    public List<Factura> getFacturasByCliente(@Argument int clienteId) {
        return facturasService.getFacturasByCliente(clienteId).getOrElseThrow(() -> new NotFoundException("Facturas no encontradas para el cliente"));
    }

    @QueryMapping
    @RolesAllowed({Constantes.PROFESIONAL})
    public List<Factura> getFacturasByProfesional(@Argument int profesionalId) {
        return facturasService.getFacturasByProfesional(profesionalId).getOrElseThrow(() -> new NotFoundException("Facturas no encontradas para el profesional"));
    }

    @QueryMapping
    @RolesAllowed({Constantes.CLIENTE})
    public Factura getFacturaById(@Argument Integer facturaId) {
        return facturasService.get(facturaId).getOrElseThrow(() -> new NotFoundException("Factura no encontrada"));
    }

    @PostMapping("/factura/add")
    @RolesAllowed({Constantes.PROFESIONAL})
    public String addFactura(@RequestBody Factura factura) {
        if (Boolean.TRUE.equals(facturasService.add(factura))) {
            return "Factura añadida exitosamente";
        } else {
            return "Factura no añadida";
        }
    }

    @PutMapping("/factura/updateEstado")
    @RolesAllowed({Constantes.CLIENTE})
    public Integer updateFacturaEstado(@RequestParam("facturaId") Integer facturaId, @RequestParam("estado") String estado) {
        return facturasService.updateEstado(facturaId, estado).getOrElseThrow(() -> new CRUDException("Estado de la factura no actualizado"));
    }
}
