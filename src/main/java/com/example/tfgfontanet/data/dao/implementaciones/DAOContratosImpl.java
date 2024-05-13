package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.common.utiles.Constantes;
import com.example.tfgfontanet.common.ErrorC;
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
    public Either<ErrorC, List<ContratoEntity>> getAll() {
        Either<ErrorC, List<ContratoEntity>> either;
        List<ContratoEntity> contratos;
        em = jpaUtil.getEntityManager();

        try {
            contratos = em.createQuery("FROM ContratoEntity", ContratoEntity.class).getResultList();
            either = Either.right(contratos);
        }

        catch(Exception e) {
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }
    @Override
    public Either<ErrorC, List<ContratoEntity>> getAllByCliente(int clienteId) {
        Either<ErrorC, List<ContratoEntity>> either;
        List<ContratoEntity> contratos;
        em = jpaUtil.getEntityManager();

        try {
            TypedQuery<ContratoEntity> query = em.createQuery("SELECT c FROM ContratoEntity c WHERE c.cliente.clienteId = :clienteId", ContratoEntity.class);
            query.setParameter("clienteId", clienteId);
            contratos = query.getResultList();
            either = Either.right(contratos);
        }

        catch(Exception e) {
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<ErrorC, List<ContratoEntity>> getAllByProfesional(int profesionalId) {
        Either<ErrorC, List<ContratoEntity>> either;
        List<ContratoEntity> contratos;
        em = jpaUtil.getEntityManager();

        try {
            TypedQuery<ContratoEntity> query = em.createQuery("SELECT c FROM ContratoEntity c WHERE c.profesional.profesionalId = :profesionalId", ContratoEntity.class);
            query.setParameter("profesionalId", profesionalId);
            contratos = query.getResultList();
            either = Either.right(contratos);
        }

        catch(Exception e) {
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<ErrorC, List<ContratoEntity>> getAllByEstado(String estado) {
        Either<ErrorC, List<ContratoEntity>> either;
        List<ContratoEntity> contratos;
        em = jpaUtil.getEntityManager();

        try {
            TypedQuery<ContratoEntity> query = em.createQuery("SELECT c FROM ContratoEntity c WHERE c.estado = :estado", ContratoEntity.class);
            query.setParameter("estado", estado);
            contratos = query.getResultList();
            either = Either.right(contratos);
        }

        catch(Exception e) {
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<ErrorC, ContratoEntity> get(int id) {
        Either<ErrorC, ContratoEntity> either;
        em = jpaUtil.getEntityManager();

        try {
            ContratoEntity contrato = em.find(ContratoEntity.class, id);
            either = Either.right(contrato);
        }

        catch(Exception e) {
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<ErrorC, Integer> add(ContratoEntity contrato) {
        Either<ErrorC, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.persist(contrato);
            tx.commit();
            either = Either.right(1);
        } catch(Exception e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }

        return either;
    }

    @Override
    public Either<ErrorC, Integer> update(ContratoEntity contrato) {
        Either<ErrorC, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.merge(contrato);
            tx.commit();
            either = Either.right(1);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

    @Override
    public Either<ErrorC, Integer> updateEstado(int contratoId, String estado) {
        Either<ErrorC, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Query query = em.createQuery("UPDATE ContratoEntity c SET c.estado = :estado WHERE c.contratoId = :contratoId");
            query.setParameter("estado", estado);
            query.setParameter("contratoId", contratoId);
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