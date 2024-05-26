package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.dao.DAOContratos;
import com.example.tfgfontanet.data.modelo.ContratoEntity;
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
public class DAOContratosImpl implements DAOContratos {

    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DAOContratosImpl(JPAUtil jpaUtil){
        this.jpaUtil =jpaUtil;
    }

    @Override
    public Either<CustomError, List<ContratoEntity>> getAll() {
        Either<CustomError, List<ContratoEntity>> either;
        List<ContratoEntity> contratos;
        em = jpaUtil.getEntityManager();

        try {
            contratos = em.createQuery("FROM ContratoEntity", ContratoEntity.class).getResultList();
            either = Either.right(contratos);
        }

        catch(Exception e) {
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }
    @Override
    public Either<CustomError, List<ContratoEntity>> getAllByCliente(int clienteId) {
        Either<CustomError, List<ContratoEntity>> either;
        List<ContratoEntity> contratos;
        em = jpaUtil.getEntityManager();

        try {
            TypedQuery<ContratoEntity> query = em.createQuery("SELECT c FROM ContratoEntity c WHERE c.cliente.clienteId = :clienteId", ContratoEntity.class);
            query.setParameter(Constantes.CLIENTE_ID, clienteId);
            contratos = query.getResultList();
            either = Either.right(contratos);
        }

        catch(Exception e) {
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<CustomError, List<ContratoEntity>> getAllByProfesional(int profesionalId) {
        Either<CustomError, List<ContratoEntity>> either;
        List<ContratoEntity> contratos;
        em = jpaUtil.getEntityManager();

        try {
            TypedQuery<ContratoEntity> query = em.createQuery("SELECT c FROM ContratoEntity c WHERE c.profesional.profesionalId = :profesionalId", ContratoEntity.class);
            query.setParameter(Constantes.PROFESIONAL_ID, profesionalId);
            contratos = query.getResultList();
            either = Either.right(contratos);
        }

        catch(Exception e) {
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<CustomError, List<ContratoEntity>> getAllByEstado(String estado) {
        Either<CustomError, List<ContratoEntity>> either;
        List<ContratoEntity> contratos;
        em = jpaUtil.getEntityManager();

        try {
            TypedQuery<ContratoEntity> query = em.createQuery("SELECT c FROM ContratoEntity c WHERE c.estado = :estado", ContratoEntity.class);
            query.setParameter(Constantes.ESTADO, estado);
            contratos = query.getResultList();
            either = Either.right(contratos);
        }

        catch(Exception e) {
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<CustomError, ContratoEntity> get(int id) {
        Either<CustomError, ContratoEntity> either;
        em = jpaUtil.getEntityManager();

        try {
            ContratoEntity contrato = em.find(ContratoEntity.class, id);
            either = Either.right(contrato);
        }

        catch(Exception e) {
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<CustomError, ContratoEntity> add(ContratoEntity contrato) {
        Either<CustomError, ContratoEntity> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.persist(contrato);
            tx.commit();
            either = Either.right(contrato);
        } catch(Exception e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }

        return either;
    }

    @Override
    public Either<CustomError, Integer> update(ContratoEntity contrato) {
        Either<CustomError, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.merge(contrato);
            tx.commit();
            either = Either.right(1);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

    @Override
    public Either<CustomError, Integer> updateEstado(int contratoId, String estado) {
        Either<CustomError, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Query query = em.createQuery("UPDATE ContratoEntity c SET c.estado = :estado WHERE c.contratoId = :contratoId");
            query.setParameter(Constantes.ESTADO, estado);
            query.setParameter(Constantes.CONTRATO_ID, contratoId);
            int updatedRows = query.executeUpdate();
            tx.commit();
            either = Either.right(updatedRows);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }
}