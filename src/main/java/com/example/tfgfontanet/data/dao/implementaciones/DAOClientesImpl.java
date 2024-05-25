package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOClientes;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import com.example.tfgfontanet.data.modelo.FavoritosEntity;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DAOClientesImpl implements DAOClientes {

    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DAOClientesImpl(JPAUtil jpaUtil){
        this.jpaUtil =jpaUtil;
    }

    @Override
    public Either<DAOError, List<ClienteEntity>> getAll() {
        Either<DAOError, List<ClienteEntity>> either;
        List<ClienteEntity> customers;
        em = jpaUtil.getEntityManager();

        try {
            customers = em.createQuery("FROM ClienteEntity", ClienteEntity.class).getResultList();
            either = Either.right(customers);
        }

        catch(Exception e) {
            either = Either.left(new DAOError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<DAOError, ClienteEntity> get(int id) {
        Either<DAOError, ClienteEntity> either;
        em = jpaUtil.getEntityManager();

        try {
            ClienteEntity cliente = em.find(ClienteEntity.class,id);
            either = Either.right(cliente);
        } catch (Exception e) {
            either = Either.left(new DAOError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<DAOError, ClienteEntity> getByUserId(int userId) {
        Either<DAOError, ClienteEntity> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            ClienteEntity clienteEntity = em.createQuery(
                            "SELECT c FROM ClienteEntity c LEFT JOIN FETCH c.profesionalesFavoritos WHERE c.usuario.userId = :userId",
                            ClienteEntity.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
            tx.commit();
            either = Either.right(clienteEntity);
        } catch (NoResultException e) {
            either = Either.left(new DAOError(404, Constantes.CLIENTE_NOT_FOUND, LocalDate.now()));
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            either = Either.left(new DAOError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

    @Override
    public Either<DAOError, Integer> add(ClienteEntity cliente) {
        Either<DAOError, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            UsuarioEntity usuario = cliente.getUsuario();
            usuario = em.merge(usuario);

            ClienteEntity cliente1 = new ClienteEntity(cliente.getClienteId(), cliente.getNombre(), cliente.getApellidos(), cliente.getNumero(), usuario);
            em.merge(cliente1);
            tx.commit();

            int rowsAffected = 1;
            either = Either.right(rowsAffected);
        }
        catch (PersistenceException e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new DAOError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

    @Override
    public Either<DAOError, Integer> update(ClienteEntity cliente) {
        Either<DAOError, Integer> either;
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
            either = Either.left(new DAOError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

    @Override
    public Either<DAOError, Integer> delete(int clienteId) {
        Either<DAOError, Integer> either;

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
                either = Either.left(new DAOError(404, Constantes.CLIENTE_NOT_FOUND, LocalDate.now()));
            }
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new DAOError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }
}
