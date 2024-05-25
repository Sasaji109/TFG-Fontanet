package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOFacturaMaterial;
import com.example.tfgfontanet.data.modelo.FacturaEntity;
import com.example.tfgfontanet.data.modelo.FacturaMaterialEntity;
import com.example.tfgfontanet.data.modelo.MaterialEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public class DAOFacturaMaterialImpl implements DAOFacturaMaterial {

    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DAOFacturaMaterialImpl(JPAUtil jpaUtil){
        this.jpaUtil =jpaUtil;
    }

    @Override
    public Either<DAOError, Integer> addFacturaMaterial(FacturaMaterialEntity facturaMaterial) {
        Either<DAOError, Integer> either;
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
                either = Either.left(new DAOError(404, Constantes.FACTURA_O_MATERIAL_NOT_FOUND, LocalDate.now()));
            }
        }
        catch (PersistenceException e) {
            if (entityTransaction.isActive()) entityTransaction.rollback();
            either = Either.left(new DAOError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }
}
