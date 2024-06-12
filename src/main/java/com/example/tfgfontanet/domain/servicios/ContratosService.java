package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.data.dao.*;
import com.example.tfgfontanet.data.modelo.*;
import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.domain.modelo.Cliente;
import com.example.tfgfontanet.domain.modelo.Contrato;
import com.example.tfgfontanet.domain.modelo.Profesional;
import com.example.tfgfontanet.domain.modelo.mapper.ClienteEntityMapper;
import com.example.tfgfontanet.domain.modelo.mapper.ContratoEntityMapper;
import com.example.tfgfontanet.domain.modelo.mapper.ProfesionalEntityMapper;
import com.example.tfgfontanet.domain.servicios.mail.MandarMailContrato;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratosService {

    private final DAOContratos daoContratos;
    private final DAOProfesionales daoProfesionales;
    private final DAOServicios daoServicios;
    private final DAOClientes daoClientes;
    private final DAOUsuario daoUsuario;
    private final ClienteEntityMapper clienteEntityMapper;
    private final ProfesionalEntityMapper profesionalEntityMapper;
    private final ContratoEntityMapper contratoEntityMapper;
    private final MandarMailContrato mandarMailContrato;

    public Either<CustomError, List<Contrato>> getAll() {
        return daoContratos.getAll().map(contratoEntityList -> {
            List<Contrato> contratos = new ArrayList<>();
            for (ContratoEntity contratoEntity : contratoEntityList) {
                Contrato contrato = contratoEntityMapper.toContrato(contratoEntity);
                contratos.add(contrato);
            }
            return contratos;
        });
    }

    public Either<CustomError, List<Contrato>> getContratosByCliente() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UsuarioEntity usuario = daoUsuario.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        Cliente cliente = daoClientes.getByUserId(usuario.getUserId()).map(clienteEntityMapper::toCliente).getOrElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        return daoContratos.getAllByCliente(cliente.getClienteId()).map(contratoEntityList -> {
            List<Contrato> contratos = new ArrayList<>();
            for (ContratoEntity contratoEntity : contratoEntityList) {
                Contrato contrato = contratoEntityMapper.toContrato(contratoEntity);
                contratos.add(contrato);
            }
            return contratos;
        });
    }

    public Either<CustomError, List<Contrato>> getContratosByProfesional() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UsuarioEntity usuario = daoUsuario.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        Profesional profesional = daoProfesionales.getByUserId(usuario.getUserId()).map(profesionalEntityMapper::toProfesional).getOrElseThrow(() -> new NotFoundException("Profesional no encontrado"));

        return daoContratos.getAllByProfesional(profesional.getProfesionalId()).map(contratoEntityList -> {
            List<Contrato> contratos = new ArrayList<>();
            for (ContratoEntity contratoEntity : contratoEntityList) {
                Contrato contrato = contratoEntityMapper.toContrato(contratoEntity);
                contratos.add(contrato);
            }
            return contratos;
        });
    }

    public Either<CustomError, List<Contrato>> getContratosByEstado(String estado) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UsuarioEntity usuario = daoUsuario.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        Profesional profesional = daoProfesionales.getByUserId(usuario.getUserId()).map(profesionalEntityMapper::toProfesional).getOrElseThrow(() -> new NotFoundException("Profesional no encontrado"));

        return daoContratos.getAllByEstado(estado).map(contratoEntityList -> {
            List<Contrato> contratos = new ArrayList<>();
            for (ContratoEntity contratoEntity : contratoEntityList) {
                if (contratoEntity.getProfesional().getProfesionalId().equals(profesional.getProfesionalId())) {
                    Contrato contrato = contratoEntityMapper.toContrato(contratoEntity);
                    contratos.add(contrato);
                }
            }
            return contratos;
        });
    }

    public Either<CustomError, Contrato> get(int id) {
        return daoContratos.get(id).map(contratoEntityMapper::toContrato);
    }

    public Boolean add(Integer profesionalId, Integer servicioId) {
        try {
            ContratoEntity contrato = new ContratoEntity();
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            UsuarioEntity usuario = daoUsuario.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException(Constantes.USUARIO_NOT_FOUND));

            ClienteEntity cliente = daoClientes.getByUserId(usuario.getUserId()).getOrElseThrow(() -> new NotFoundException(Constantes.CLIENTE_NOT_FOUND));
            ProfesionalEntity profesional = daoProfesionales.get(profesionalId).getOrElseThrow(() -> new NotFoundException(Constantes.PROFESIONAL_NOT_FOUND));
            ServicioEntity servicio = daoServicios.get(servicioId).getOrElseThrow(() -> new NotFoundException(Constantes.SERVICIO_NOT_FOUND));

            contrato.setCliente(cliente);
            contrato.setProfesional(profesional);
            contrato.setServicio(servicio);
            contrato.setFechaInicio(LocalDate.now());
            contrato.setFechaFin(LocalDate.now());
            contrato.setEstado(Constantes.PENDIENTE);

            ContratoEntity contratoEntity = daoContratos.add(contrato).get();
            mandarMailContrato.mandarMailContratoProfesionalPDF(contratoEntity.getContratoId());
            return true;
        } catch (CRUDException e) {
            return false;
        }
    }

    public Either<CustomError, Integer> update(Contrato contrato) {
        ContratoEntity contratoEntity = contratoEntityMapper.toContratoEntity(contrato);
        return daoContratos.update(contratoEntity);
    }

    public Either<CustomError, Integer> updateEstado(int contratoId) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UsuarioEntity usuario = daoUsuario.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException(Constantes.USUARIO_NOT_FOUND));
        if (usuario.getRole().equals(Constantes.CLIENTE)) {
            Either<CustomError, Integer> resultado = daoContratos.updateEstado(contratoId, Constantes.VIGENTE);
            if (resultado.isRight()) {
                mandarMailContrato.mandarMailAvisoContratoVigente(contratoId);
            }
            return resultado;
        } else if (usuario.getRole().equals(Constantes.PROF)) {
            Either<CustomError, Integer> resultado = daoContratos.updateEstado(contratoId, Constantes.ACEPTADO);
            if (resultado.isRight()) {
                mandarMailContrato.mandarMailContratoClientePDF(contratoId);
            }
            return resultado;
        }
        return Either.left(new CustomError(10, Constantes.NO_PERMISOS, LocalDate.now()));
    }
}
