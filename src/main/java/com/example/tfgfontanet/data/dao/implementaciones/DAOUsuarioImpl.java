package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.common.utiles.Constantes;
import com.example.tfgfontanet.data.dao.DAOUsuario;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public class DAOUsuarioImpl implements DAOUsuario {

    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DAOUsuarioImpl(JPAUtil jpaUtil){
        this.jpaUtil =jpaUtil;
    }


    @Override
    public UsuarioEntity findByUsername(String name) {
        em = jpaUtil.getEntityManager();
        TypedQuery<UsuarioEntity> query = em.createQuery(
                "SELECT u FROM UsuarioEntity u WHERE u.username = :name", UsuarioEntity.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public UsuarioEntity getUsuarioByCorreo(String correoUsuario) {
        em = jpaUtil.getEntityManager();
        TypedQuery<UsuarioEntity> query = em.createQuery(
                "SELECT u FROM UsuarioEntity u WHERE u.correo = :correo", UsuarioEntity.class);
        query.setParameter("correo", correoUsuario);
        return query.getSingleResult();
    }

    @Override
    public UsuarioEntity getUsuarioByCodigo(String codigoActivacion) {
        em = jpaUtil.getEntityManager();
        TypedQuery<UsuarioEntity> query = em.createQuery(
                "SELECT u FROM UsuarioEntity u WHERE u.codigoActivacion = :codigo", UsuarioEntity.class);
        query.setParameter("codigo", codigoActivacion);
        return query.getSingleResult();
    }

    @Override
    public Either<ErrorC, Integer> registrarUsuario(UsuarioEntity usuario) {
        Either<ErrorC, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(usuario);
            tx.commit();

            int rowsAffected = 1;
            either = Either.right(rowsAffected);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

    @Override
    public Either<ErrorC, Integer> updateUsuario(UsuarioEntity usuario) {
        Either<ErrorC, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();

        try {
            entityTransaction.begin();
            em.merge(usuario);
            entityTransaction.commit();

            int rowsAffected = 1;
            either = Either.right(rowsAffected);
        } catch (PersistenceException e) {
            if (entityTransaction.isActive()) entityTransaction.rollback();
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }
}
