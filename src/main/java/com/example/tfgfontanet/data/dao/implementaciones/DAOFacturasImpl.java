package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.common.utiles.Constantes;
import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOFacturas;
import com.example.tfgfontanet.data.modelo.FacturaEntity;
import com.example.tfgfontanet.data.modelo.FacturaMaterialEntity;
import com.example.tfgfontanet.data.modelo.MaterialEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DAOFacturasImpl implements DAOFacturas {

    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DAOFacturasImpl(JPAUtil jpaUtil){
        this.jpaUtil =jpaUtil;
    }

    @Override
    public Either<ErrorC, List<FacturaEntity>> getAll() {
        Either<ErrorC, List<FacturaEntity>> either;
        List<FacturaEntity> facturas;
        em = jpaUtil.getEntityManager();

        try {
            facturas = em.createQuery("FROM FacturaEntity", FacturaEntity.class).getResultList();
            either = Either.right(facturas);
        }

        catch(Exception e) {
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<ErrorC, List<FacturaEntity>> getAllByCliente(int clienteId) {
        Either<ErrorC, List<FacturaEntity>> either;
        List<FacturaEntity> facturas;
        em = jpaUtil.getEntityManager();

        try {
            TypedQuery<FacturaEntity> query = em.createQuery("SELECT f FROM FacturaEntity f WHERE f.cliente.clienteId = :clienteId", FacturaEntity.class);
            query.setParameter("clienteId", clienteId);
            facturas = query.getResultList();
            either = Either.right(facturas);
        }

        catch(Exception e) {
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<ErrorC, List<FacturaEntity>> getAllByProfesional(int profesionalId) {
        Either<ErrorC, List<FacturaEntity>> either;
        List<FacturaEntity> facturas;
        em = jpaUtil.getEntityManager();

        try {
            TypedQuery<FacturaEntity> query = em.createQuery("SELECT f FROM FacturaEntity f WHERE f.profesional.profesionalId = :profesionalId", FacturaEntity.class);
            query.setParameter("profesionalId", profesionalId);
            facturas = query.getResultList();
            either = Either.right(facturas);
        }

        catch(Exception e) {
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<ErrorC, FacturaEntity> get(int id) {
        Either<ErrorC, FacturaEntity> either;
        em = jpaUtil.getEntityManager();

        try {
            FacturaEntity factura = em.find(FacturaEntity.class, id);
            either = Either.right(factura);
        }

        catch(Exception e) {
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<ErrorC, Integer> add(FacturaEntity factura) {
        Either<ErrorC, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Paso 1: Calcular el precio total de los materiales
            Double precioMateriales = 0.0;
            for (FacturaMaterialEntity facturaMaterial : factura.getMateriales()) {
                MaterialEntity material = facturaMaterial.getMaterial();
                // Acceder a la BBDD para obtener la información completa del material
                MaterialEntity materialCompleto = em.find(MaterialEntity.class, material.getMaterialId());
                // Sumar el precio del material multiplicado por la cantidad
                precioMateriales += materialCompleto.getPrecio() * facturaMaterial.getCantidad();
            }

            // Paso 2: Sumar el precio del servicio al precio total
            precioMateriales += factura.getServicio().getTarifaBase();

            // Paso 3: Actualizar el precio total de la factura
            factura.setPrecio(precioMateriales);

            // Paso 4: Añadir la factura a la BBDD
            em.persist(factura);

            // Paso 5: Obtener la id de la factura recién añadida
            Integer facturaId = factura.getFacturaId();

            // Paso 6: Actualizar las ids de los objetos FacturaMaterial y añadirlos a la BBDD
            for (FacturaMaterialEntity facturaMaterial : factura.getMateriales()) {
                facturaMaterial.setFacturaId(facturaId);
                em.persist(facturaMaterial);
            }

            tx.commit();
            either = Either.right(facturaId);
        } catch(Exception e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }

        return either;
    }

    @Override
    public Either<ErrorC, Integer> updateEstado(int facturaId, String estado) {
        Either<ErrorC, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Query query = em.createQuery("UPDATE FacturaEntity f SET f.estado = :estado WHERE f.facturaId = :facturaId");
            query.setParameter("estado", estado);
            query.setParameter("facturaId", facturaId);
            int updatedRows = query.executeUpdate();
            tx.commit();
            either = Either.right(updatedRows);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }
}
