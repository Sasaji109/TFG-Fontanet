package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOProfesionales;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DAOProfesionalesImpl implements DAOProfesionales {

    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DAOProfesionalesImpl(JPAUtil jpaUtil){
        this.jpaUtil =jpaUtil;
    }

    @Override
    public Either<DAOError, List<ProfesionalEntity>> getAll() {
        Either<DAOError, List<ProfesionalEntity>> either;
        List<ProfesionalEntity> profesionales;
        em = jpaUtil.getEntityManager();

        try {
            profesionales = em.createQuery("FROM ProfesionalEntity", ProfesionalEntity.class).getResultList();
            either = Either.right(profesionales);
        }
        catch(Exception e) {
            either = Either.left(new DAOError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<DAOError, List<ProfesionalEntity>> getAllByExp(int experiencia) {
        Either<DAOError, List<ProfesionalEntity>> either;
        List<ProfesionalEntity> profesionales;
        em = jpaUtil.getEntityManager();

        try {
            String query = "FROM ProfesionalEntity WHERE experiencia = :experiencia";
            profesionales = em.createQuery(query, ProfesionalEntity.class)
                    .setParameter("experiencia", experiencia)
                    .getResultList();
            either = Either.right(profesionales);
        }
        catch(Exception e) {
            either = Either.left(new DAOError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<DAOError, List<ProfesionalEntity>> getAllByOficio(String oficio) {
        Either<DAOError, List<ProfesionalEntity>> either;
        List<ProfesionalEntity> profesionales;
        em = jpaUtil.getEntityManager();

        try {
            String query = "FROM ProfesionalEntity WHERE oficio = :oficio";
            profesionales = em.createQuery(query, ProfesionalEntity.class)
                    .setParameter("oficio", oficio)
                    .getResultList();
            either = Either.right(profesionales);
        }
        catch(Exception e) {
            either = Either.left(new DAOError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<DAOError, List<ProfesionalEntity>> getAllByDisp(String disponibilidad) {
        Either<DAOError, List<ProfesionalEntity>> either;
        List<ProfesionalEntity> profesionales;
        em = jpaUtil.getEntityManager();

        try {
            String query = "FROM ProfesionalEntity WHERE disponibilidad = :disponibilidad";
            profesionales = em.createQuery(query, ProfesionalEntity.class)
                    .setParameter("disponibilidad", disponibilidad)
                    .getResultList();
            either = Either.right(profesionales);
        }
        catch(Exception e) {
            either = Either.left(new DAOError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<DAOError, List<ProfesionalEntity>> getAllByVal(int valoracion) {
        Either<DAOError, List<ProfesionalEntity>> either;
        List<ProfesionalEntity> profesionales;
        em = jpaUtil.getEntityManager();

        try {
            String query = "FROM ProfesionalEntity WHERE valoracion = :valoracion";
            profesionales = em.createQuery(query, ProfesionalEntity.class)
                    .setParameter("valoracion", valoracion)
                    .getResultList();
            either = Either.right(profesionales);
        }
        catch(Exception e) {
            either = Either.left(new DAOError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<DAOError, ProfesionalEntity> get(int id) {
        Either<DAOError, ProfesionalEntity> either;
        em = jpaUtil.getEntityManager();

        try {
            ProfesionalEntity profesional = em.find(ProfesionalEntity.class,id);
            either = Either.right(profesional);
        } catch (Exception e) {
            either = Either.left(new DAOError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        }
        return either;
    }

    @Override
    public Either<DAOError, Integer> add(ProfesionalEntity profesional) {
        Either<DAOError, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            UsuarioEntity usuario = profesional.getUsuario();
            usuario = em.merge(usuario);

            ProfesionalEntity profesional1 = new ProfesionalEntity(profesional.getProfesionalId(), profesional.getNombre(), profesional.getApellidos(),
                    profesional.getNumero(), profesional.getExperiencia(), profesional.getDisponibilidad(), profesional.getOficio(), profesional.getValoracion(), usuario);
            em.merge(profesional1);
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
    public Either<DAOError, Integer> update(ProfesionalEntity profesional) {
        Either<DAOError, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(profesional);
            em.merge(profesional.getUsuario());
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
    public Either<DAOError, Integer> updateVal(int id, int val) {
        Either<DAOError, Integer> either;
        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            ProfesionalEntity profesional = em.find(ProfesionalEntity.class, id);
            if (profesional != null) {
                profesional.setValoracion(val);
                em.merge(profesional);
                tx.commit();

                int rowsAffected = 1;
                either = Either.right(rowsAffected);
            } else {
                either = Either.left(new DAOError(404, "Profesional no encontrado", LocalDate.now()));
            }
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            either = Either.left(new DAOError(5, Constantes.SQL_ERROR + e.getMessage(), LocalDate.now()));
        } finally {
            em.close();
        }
        return either;
    }

    @Override
    public Either<DAOError, Integer> delete(int profesionalId) {
        Either<DAOError, Integer> either;

        em = jpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            ProfesionalEntity profesional = em.find(ProfesionalEntity.class, profesionalId);
            if (profesional != null) {
                em.createQuery("DELETE FROM FavoritosEntity f WHERE f.profesional = :profesional")
                        .setParameter("profesional", profesional)
                        .executeUpdate();

                UsuarioEntity usuario = profesional.getUsuario();
                em.remove(profesional);
                em.remove(usuario);
                tx.commit();

                int rowsAffected = 1;
                either = Either.right(rowsAffected);
            } else {
                either = Either.left(new DAOError(404, "Profesional no encontrado", LocalDate.now()));
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
