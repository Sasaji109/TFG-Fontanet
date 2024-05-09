package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.common.utiles.Constantes;
import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOFacturaMaterial;
import com.example.tfgfontanet.data.modelo.FacturaEntity;
import com.example.tfgfontanet.data.modelo.FacturaMaterialEntity;
import com.example.tfgfontanet.data.modelo.MaterialEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;

import java.time.LocalDate;

public class DAOFacturaMaterialImpl implements DAOFacturaMaterial {

    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DAOFacturaMaterialImpl(JPAUtil jpaUtil){
        this.jpaUtil =jpaUtil;
    }

    @Override
    public Either<ErrorC, Integer> addFacturaMaterial(FacturaMaterialEntity facturaMaterial) {
        Either<ErrorC, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();

        try {
            entityTransaction.begin();

            FacturaEntity factura = em.find(FacturaEntity.class, facturaMaterial.getFacturaId());
            MaterialEntity material = em.find(MaterialEntity.class, facturaMaterial.getMaterial().getMaterialId());

            if (factura != null && material != null) {
                em.persist(facturaMaterial);
                entityTransaction.commit();

                int rowsAffected = 1;
                either = Either.right(rowsAffected);
            } else {
                either = Either.left(new ErrorC(404, "Factura o Material no encontrado", LocalDate.now()));
            }
        }
        catch (PersistenceException e) {
            if (entityTransaction.isActive()) entityTransaction.rollback();
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }
}
