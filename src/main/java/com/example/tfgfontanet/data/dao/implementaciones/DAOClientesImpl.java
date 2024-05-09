package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.common.utiles.Constantes;
import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOClientes;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import com.example.tfgfontanet.data.modelo.FavoritosEntity;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.List;

public class DAOClientesImpl implements DAOClientes {

    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DAOClientesImpl(JPAUtil jpaUtil){
        this.jpaUtil =jpaUtil;
    }

    @Override
    public Either<ErrorC, List<ClienteEntity>> getAll() {
        Either<ErrorC, List<ClienteEntity>> either;
        List<ClienteEntity> customers;
        em = jpaUtil.getEntityManager();

        try {
            customers = em.createQuery("FROM ClienteEntity", ClienteEntity.class).getResultList();
            either = Either.right(customers);
        }

        catch(Exception e) {
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }


    @Override
    public Either<ErrorC, ClienteEntity> get(int id) {
        Either<ErrorC, ClienteEntity> either;
        em = jpaUtil.getEntityManager();

        try {
            ClienteEntity cliente = em.find(ClienteEntity.class,id);
            either = Either.right(cliente);
        } catch (Exception e) {
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<ErrorC, Integer> add(ClienteEntity cliente) {
        Either<ErrorC, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();

        try {
            entityTransaction.begin();

            UsuarioEntity usuario = cliente.getUsuario();
            em.persist(usuario);

            ClienteEntity cliente1 = new ClienteEntity(cliente.getClienteId(), cliente.getNombre(), cliente.getApellidos(), cliente.getNumero(), cliente.getUsuario());
            em.persist(cliente1);
            entityTransaction.commit();

            int rowsAffected = 1;
            either = Either.right(rowsAffected);
        }
        catch (PersistenceException e) {
            if (entityTransaction.isActive()) entityTransaction.rollback();
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

    @Override
    public Either<ErrorC, Integer> update(ClienteEntity cliente) {
        Either<ErrorC, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(cliente);

            for (FavoritosEntity favorito : cliente.getProfesionalesFavoritos()) {
                em.merge(favorito);
            }

            em.merge(cliente.getUsuario());
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
    public Either<ErrorC, Integer> delete(int clienteId) {
        Either<ErrorC, Integer> either;

        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            ClienteEntity cliente = em.find(ClienteEntity.class, clienteId);
            if (cliente != null) {
                em.createQuery("DELETE FROM FavoritosEntity f WHERE f.clienteId = :clienteId")
                        .setParameter("clienteId", clienteId)
                        .executeUpdate();
                UsuarioEntity usuario = cliente.getUsuario();
                em.remove(cliente);
                em.remove(usuario);
                tx.commit();

                int rowsAffected = 1;
                either = Either.right(rowsAffected);
            } else {
                either = Either.left(new ErrorC(404, "Cliente no encontrado", LocalDate.now()));
            }
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new ErrorC(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }
}
