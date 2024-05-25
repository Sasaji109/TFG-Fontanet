package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOClientes;
import com.example.tfgfontanet.data.dao.DAOContratos;
import com.example.tfgfontanet.data.dao.DAOProfesionales;
import com.example.tfgfontanet.data.dao.DAOUsuario;
import com.example.tfgfontanet.data.modelo.ContratoEntity;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import com.example.tfgfontanet.domain.modelo.Cliente;
import com.example.tfgfontanet.domain.modelo.Contrato;
import com.example.tfgfontanet.domain.modelo.Profesional;
import com.example.tfgfontanet.domain.modelo.mapper.ClienteEntityMapper;
import com.example.tfgfontanet.domain.modelo.mapper.ContratoEntityMapper;
import com.example.tfgfontanet.domain.modelo.mapper.ProfesionalEntityMapper;
import com.example.tfgfontanet.domain.servicios.mail.MandarMailContratoPDF;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratosService {

    private final DAOContratos daoContratos;
    private final DAOProfesionales daoProfesionales;
    private final DAOClientes daoClientes;
    private final DAOUsuario daoUsuario;
    private final ClienteEntityMapper clienteEntityMapper;
    private final ProfesionalEntityMapper profesionalEntityMapper;
    private final ContratoEntityMapper contratoEntityMapper;
    private final MandarMailContratoPDF mandarMailContratoPDF;

    public Either<DAOError, List<Contrato>> getAll() {
        return daoContratos.getAll().map(contratoEntityList -> {
            List<Contrato> contratos = new ArrayList<>();
            for (ContratoEntity contratoEntity : contratoEntityList) {
                Contrato contrato = contratoEntityMapper.toContrato(contratoEntity);
                contratos.add(contrato);
            }
            return contratos;
        });
    }

    public Either<DAOError, List<Contrato>> getContratosByCliente() {
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

    public Either<DAOError, List<Contrato>> getContratosByProfesional() {
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

    public Either<DAOError, List<Contrato>> getContratosByEstado(String estado) {
        return daoContratos.getAllByEstado(estado).map(contratoEntityList -> {
            List<Contrato> contratos = new ArrayList<>();
            for (ContratoEntity contratoEntity : contratoEntityList) {
                Contrato contrato = contratoEntityMapper.toContrato(contratoEntity);
                contratos.add(contrato);
            }
            return contratos;
        });
    }

    public Either<DAOError, Contrato> get(int id) {
        return daoContratos.get(id).map(contratoEntityMapper::toContrato);
    }

    public Boolean add(Contrato contrato) {
        try {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            UsuarioEntity usuario = daoUsuario.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException(Constantes.USUARIO_NOT_FOUND));
            Cliente cliente = daoClientes.getByUserId(usuario.getUserId()).map(clienteEntityMapper::toCliente).getOrElseThrow(() -> new NotFoundException(Constantes.CLIENTE_NOT_FOUND));
            contrato.setCliente(cliente);
            ContratoEntity contratoEntity = daoContratos.add(contratoEntityMapper.toContratoEntity(contrato)).get();
            mandarMailContratoPDF.mandarMail(contratoEntity.getContratoId());
            return true;
        } catch (CRUDException e) {
            return false;
        }
    }

    public Either<DAOError, Integer> update(Contrato contrato) {
        ContratoEntity contratoEntity = contratoEntityMapper.toContratoEntity(contrato);
        return daoContratos.update(contratoEntity);
    }

    public Either<DAOError, Integer> updateEstado(int contratoId, String estado) {
        return daoContratos.updateEstado(contratoId, estado);
    }
}
