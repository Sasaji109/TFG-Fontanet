package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.dao.DAOServicios;
import com.example.tfgfontanet.data.modelo.ServicioEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DAOServiciosImpl implements DAOServicios {

    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DAOServiciosImpl(JPAUtil jpaUtil){
        this.jpaUtil =jpaUtil;
    }

    @Override
    public Either<CustomError, List<ServicioEntity>> getAll() {
        Either<CustomError, List<ServicioEntity>> either;
        List<ServicioEntity> servicios;
        em = jpaUtil.getEntityManager();

        try {
            servicios = em.createQuery("FROM ServicioEntity", ServicioEntity.class).getResultList();
            either = Either.right(servicios);
        }
        catch(Exception e) {
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<CustomError, ServicioEntity> get(int id) {
        Either<CustomError, ServicioEntity> either;
        em = jpaUtil.getEntityManager();

        try {
            ServicioEntity servicio = em.find(ServicioEntity.class, id);
            if (servicio != null) {
                either = Either.right(servicio);
            } else {
                either = Either.left(new CustomError(404, Constantes.SERVICIO_NOT_FOUND, LocalDate.now()));
            }
        } catch (Exception e) {
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

    @Override
    public Either<CustomError, Integer> add(ServicioEntity servicio) {
        Either<CustomError, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(servicio);
            tx.commit();

            int rowsAffected = 1;
            either = Either.right(rowsAffected);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

    @Override
    public Either<CustomError, Integer> update(ServicioEntity servicio) {
        Either<CustomError, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction ex = em.getTransaction();

        try {
            ex.begin();
            em.merge(servicio);
            ex.commit();

            int rowsAffected = 1;
            either = Either.right(rowsAffected);
        } catch (Exception e) {
            if (ex.isActive()) ex.rollback();
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

        try {
            tx.begin();
            ServicioEntity servicio = em.find(ServicioEntity.class, id);
            if (servicio != null) {
                em.remove(servicio);
                tx.commit();
                int rowsAffected = 1;
                either = Either.right(rowsAffected);
            } else {
                either = Either.left(new CustomError(404, Constantes.SERVICIO_NOT_FOUND, LocalDate.now()));
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
