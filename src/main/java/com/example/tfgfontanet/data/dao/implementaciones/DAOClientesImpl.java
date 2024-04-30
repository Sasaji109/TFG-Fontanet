package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.common.utiles.Constantes;
import com.example.tfgfontanet.data.dao.DAOClientes;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
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
}
