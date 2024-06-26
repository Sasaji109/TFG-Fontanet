package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.dao.DAOMateriales;
import com.example.tfgfontanet.data.modelo.MaterialEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DAOMaterialesImpl implements DAOMateriales {

    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DAOMaterialesImpl(JPAUtil jpaUtil){
        this.jpaUtil =jpaUtil;
    }

    @Override
    public Either<CustomError, List<MaterialEntity>> getAll() {
        Either<CustomError, List<MaterialEntity>> either;
        List<MaterialEntity> materiales;
        em = jpaUtil.getEntityManager();

        try {
            materiales = em.createQuery("FROM MaterialEntity", MaterialEntity.class).getResultList();
            either = Either.right(materiales);
        }
        catch(Exception e) {
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }


    @Override
    public Either<CustomError, MaterialEntity> get(int id) {
        Either<CustomError, MaterialEntity> either;
        em = jpaUtil.getEntityManager();

        try {
            MaterialEntity material = em.find(MaterialEntity.class, id);
            if (material != null) {
                either = Either.right(material);
            } else {
                either = Either.left(new CustomError(404, Constantes.MATERIAL_NOT_FOUND, LocalDate.now()));
            }
        } catch (Exception e) {
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

    @Override
    public Either<CustomError, Integer> add(MaterialEntity material) {
        Either<CustomError, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();

        try {
            entityTransaction.begin();
            em.merge(material);
            entityTransaction.commit();

            int rowsAffected = 1;
            either = Either.right(rowsAffected);
        } catch (PersistenceException e) {
            if (entityTransaction.isActive()) entityTransaction.rollback();
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

    @Override
    public Either<CustomError, Integer> update(MaterialEntity material) {
        Either<CustomError, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();

        try {
            entityTransaction.begin();
            em.merge(material);
            entityTransaction.commit();

            int rowsAffected = 1;
            either = Either.right(rowsAffected);
        } catch (PersistenceException e) {
            if (entityTransaction.isActive()) entityTransaction.rollback();
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

    @Override
    public Either<CustomError, Integer> delete(int id) {
        Either<CustomError, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            MaterialEntity material = em.find(MaterialEntity.class, id);
            if (material != null) {
                em.remove(material);
                tx.commit();

                int rowsAffected = 1;
                either = Either.right(rowsAffected);
            } else {
                either = Either.left(new CustomError(404, Constantes.MATERIAL_NOT_FOUND, LocalDate.now()));
            }
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }
}
