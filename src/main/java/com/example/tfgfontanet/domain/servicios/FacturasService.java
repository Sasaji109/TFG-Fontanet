package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.*;
import com.example.tfgfontanet.domain.modelo.mapper.ServicioEntityMapper;
import com.example.tfgfontanet.domain.servicios.mail.MandarMailFactura;
import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.dao.*;
import com.example.tfgfontanet.data.modelo.FacturaEntity;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import com.example.tfgfontanet.domain.modelo.mapper.ClienteEntityMapper;
import com.example.tfgfontanet.domain.modelo.mapper.FacturaEntityMapper;
import com.example.tfgfontanet.domain.modelo.mapper.ProfesionalEntityMapper;
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
public class FacturasService {

    private final DAOProfesionales daoProfesionales;
    private final DAOClientes daoClientes;
    private final DAOUsuario daoUsuario;
    private final ClienteEntityMapper clienteEntityMapper;
    private final ProfesionalEntityMapper profesionalEntityMapper;
    private final DAOFacturas daoFacturas;
    private final FacturaEntityMapper facturaEntityMapper;
    private final MandarMailFactura mandarMailFactura;

    public Either<CustomError, List<Factura>> getAll() {
        return daoFacturas.getAll().map(facturaEntityList -> {
            List<Factura> facturas = new ArrayList<>();
            for (FacturaEntity facturaEntity : facturaEntityList) {
                Factura cliente = facturaEntityMapper.toFactura(facturaEntity);
                facturas.add(cliente);
            }
            return facturas;
        });
    }

    public Either<CustomError, List<Factura>> getFacturasByCliente() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UsuarioEntity usuario = daoUsuario.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException(Constantes.USUARIO_NOT_FOUND));
        Cliente cliente = daoClientes.getByUserId(usuario.getUserId()).map(clienteEntityMapper::toCliente).getOrElseThrow(() -> new NotFoundException(Constantes.CLIENTE_NOT_FOUND));

        return daoFacturas.getAllByCliente(cliente.getClienteId()).map(facturaEntityList -> {
            List<Factura> facturas = new ArrayList<>();
            for (FacturaEntity facturaEntity : facturaEntityList) {
                Factura factura = facturaEntityMapper.toFactura(facturaEntity);
                facturas.add(factura);
            }
            return facturas;
        });
    }

    public Either<CustomError, List<Factura>> getFacturasByProfesional() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UsuarioEntity usuario = daoUsuario.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException(Constantes.USUARIO_NOT_FOUND));
        Profesional profesional = daoProfesionales.getByUserId(usuario.getUserId()).map(profesionalEntityMapper::toProfesional).getOrElseThrow(() -> new NotFoundException(Constantes.PROFESIONAL_NOT_FOUND));

        return daoFacturas.getAllByProfesional(profesional.getProfesionalId()).map(facturaEntityList -> {
            List<Factura> facturas = new ArrayList<>();
            for (FacturaEntity facturaEntity : facturaEntityList) {
                Factura factura = facturaEntityMapper.toFactura(facturaEntity);
                facturas.add(factura);
            }
            return facturas;
        });
    }

    public Either<CustomError, Factura> get(int id) {
        return daoFacturas.get(id).map(facturaEntityMapper::toFactura);
    }

    public Boolean add(FacturaInput facturaInput) {
        try {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            UsuarioEntity usuario = daoUsuario.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException(Constantes.USUARIO_NOT_FOUND));
            Profesional profesional = daoProfesionales.getByUserId(usuario.getUserId()).map(profesionalEntityMapper::toProfesional).getOrElseThrow(() -> new NotFoundException(Constantes.PROFESIONAL_NOT_FOUND));
            Cliente cliente = daoClientes.get(facturaInput.getClienteId()).map(clienteEntityMapper::toCliente).getOrElseThrow(() -> new NotFoundException(Constantes.CLIENTES_NOT_FOUND));

            Factura factura = new Factura(facturaInput.getFacturaId(), cliente, profesional, facturaInput.getServicio(), facturaInput.getMateriales(), facturaInput.getPrecio(), "pendiente");
            FacturaEntity facturaEntity = daoFacturas.add(facturaEntityMapper.toFacturaEntity(factura)).get();
            mandarMailFactura.mandarMailFacturaProfesionalPDF(facturaEntity.getFacturaId());
            mandarMailFactura.mandarMailFacturaClientePDF(facturaEntity.getFacturaId());
            return true;
        } catch (CRUDException e) {
            return false;
        }
    }

    public Either<CustomError, Integer> updateEstado(int facturaId) {
        Either<CustomError, Integer> resultado = daoFacturas.updateEstado(facturaId, Constantes.PAGADA);
        if (resultado.isRight()) {
            mandarMailFactura.mandarMailAvisoFacturaPagada(facturaId);
        }
        return resultado;
    }
}
