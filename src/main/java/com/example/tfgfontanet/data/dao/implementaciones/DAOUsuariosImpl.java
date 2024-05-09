package com.example.tfgfontanet.data.dao.implementaciones;

import com.example.tfgfontanet.common.configuracion.JPAUtil;
import com.example.tfgfontanet.data.dao.DAOUsuarios;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class DAOUsuariosImpl implements DAOUsuarios {

    private final JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public DAOUsuariosImpl(JPAUtil jpaUtil){
        this.jpaUtil =jpaUtil;
    }
}
