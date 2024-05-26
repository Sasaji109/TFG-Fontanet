package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.dao.DAOFavoritos;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import com.example.tfgfontanet.data.modelo.FavoritosEntity;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DAOFavoritosImpl implements DAOFavoritos {

    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DAOFavoritosImpl(JPAUtil jpaUtil){
        this.jpaUtil =jpaUtil;
    }

    @Override
    public Either<CustomError, List<FavoritosEntity>> getAllByCliente(int clienteId) {
        Either<CustomError, List<FavoritosEntity>> either;
        List<FavoritosEntity> favoritos;
        em = jpaUtil.getEntityManager();

        try {
            String query = "FROM FavoritosEntity WHERE clienteId = :clienteId";
            favoritos = em.createQuery(query, FavoritosEntity.class)
                    .setParameter(Constantes.CLIENTE_ID, clienteId)
                    .getResultList();
            either = Either.right(favoritos);
        }
        catch(Exception e) {
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<CustomError, Integer> addFavorito(int clienteId, int profesionalId) {
        Either<CustomError, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();

        try {
            entityTransaction.begin();

            ClienteEntity cliente = em.find(ClienteEntity.class, clienteId);
            ProfesionalEntity profesional = em.find(ProfesionalEntity.class, profesionalId);

            if (cliente != null && profesional != null) {
                FavoritosEntity favorito = new FavoritosEntity();
                favorito.setClienteId(clienteId);
                favorito.setProfesional(profesional);
                em.persist(favorito);
                entityTransaction.commit();

                int rowsAffected = 1;
                either = Either.right(rowsAffected);
            } else {
                either = Either.left(new CustomError(404, Constantes.CLIENTE_O_PROFESIONAL_NOT_FOUND, LocalDate.now()));
            }
        }
        catch (PersistenceException e) {
            if (entityTransaction.isActive()) entityTransaction.rollback();
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

    @Override
    public Either<CustomError, Integer> deleteFavorito(int clienteId, int profesionalId) {
        Either<CustomError, Integer> either;

        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            TypedQuery<FavoritosEntity> query = em.createQuery("SELECT f FROM FavoritosEntity f WHERE f.clienteId = :clienteId AND f.profesional.id = :profesionalId", FavoritosEntity.class);
            query.setParameter(Constantes.CLIENTE_ID, clienteId);
            query.setParameter(Constantes.PROFESIONAL_ID, profesionalId);
            FavoritosEntity favorito = query.getSingleResult();

            if (favorito != null) {
                em.remove(favorito);
                tx.commit();

                int rowsAffected = 1;
                either = Either.right(rowsAffected);
            } else {
                either = Either.left(new CustomError(404, Constantes.FAVORITO_NOT_FOUND, LocalDate.now()));
            }
        } catch (NoResultException e) {
            either = Either.left(new CustomError(404, Constantes.FAVORITO_NOT_FOUND, LocalDate.now()));
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new CustomError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

}
