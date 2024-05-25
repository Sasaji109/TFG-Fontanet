package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.Contrato;
import com.example.tfgfontanet.domain.servicios.ContratosService;
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
public class ContratosController {

    private final ContratosService contratosService;

    @QueryMapping
    public List<Contrato> getAllContratos() {
        return contratosService.getAll().getOrElseThrow(() -> new NotFoundException(Constantes.CLIENTES_NOT_FOUND));
    }

    @QueryMapping
    @RolesAllowed({Constantes.CLIENTE})
    public List<Contrato> getContratosByCliente() {
        return contratosService.getContratosByCliente().getOrElseThrow(() -> new NotFoundException(Constantes.CONTRATOS_NO_ENCONTRADOS_PARA_EL_CLIENTE));
    }

    @QueryMapping
    @RolesAllowed({Constantes.PROFESIONAL})
    public List<Contrato> getContratosByProfesional() {
        return contratosService.getContratosByProfesional().getOrElseThrow(() -> new NotFoundException(Constantes.CONTRATOS_NO_ENCONTRADOS_PARA_EL_PROFESIONAL));
    }

    @QueryMapping
    @RolesAllowed({Constantes.PROFESIONAL})
    public List<Contrato> getContratosByEstado(@Argument String estado) {
        return contratosService.getContratosByEstado(estado).getOrElseThrow(() -> new NotFoundException(Constantes.CONTRATOS_NO_ENCONTRADOS_PARA_EL_ESTADO_ESPECIFICADO));
    }

    @QueryMapping
    @RolesAllowed({Constantes.CLIENTE, Constantes.PROFESIONAL})
    public Contrato getContratoById(@Argument Integer contratoId) {
        return contratosService.get(contratoId).getOrElseThrow(() -> new NotFoundException(Constantes.CONTRATO_NOT_FOUND));
    }

    @PostMapping(Constantes.CONTRATO_ADD_PATH)
    @RolesAllowed({Constantes.CLIENTE})
    public String addContrato(@RequestBody Contrato contrato) {
        if (Boolean.TRUE.equals(contratosService.add(contrato))) {
            return Constantes.CONTRATO_ANADIDO_EXITOSAMENTE;
        } else {
            return Constantes.CONTRATO_NO_ANADIDO;
        }
    }

    @PutMapping(Constantes.CONTRATO_UPDATE_PATH)
    @RolesAllowed({Constantes.CLIENTE, Constantes.PROFESIONAL})
    public Integer updateContrato(@RequestBody Contrato contrato) {
        return contratosService.update(contrato).getOrElseThrow(() -> new CRUDException(Constantes.CONTRATO_NO_ACTUALIZADO));
    }

    @PutMapping(Constantes.CONTRATO_UPDATE_ESTADO_PATH)
    public Integer updateContratoEstado(@RequestParam(Constantes.CONTRATO_ID) Integer contratoId, @RequestParam(Constantes.ESTADO) String estado) {
        return contratosService.updateEstado(contratoId, estado).getOrElseThrow(() -> new CRUDException(Constantes.ESTADO_DEL_CONTRATO_NO_ACTUALIZADO));
    }
}
