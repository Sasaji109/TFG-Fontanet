package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.data.repositorios.DAOEquipos;
import com.example.tfgfontanet.domain.modelo.Equipo;
import com.example.tfgfontanet.domain.modelo.Jugador;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquiposService {

    private final DAOEquipos daoEquipos;


    public EquiposService(DAOEquipos daoEquipos) {
        this.daoEquipos = daoEquipos;
    }

    public List<Equipo> getAllEquipos() {
        return daoEquipos.getAllEquipos();
    }

    public Optional<Equipo> getEquipoById(Integer id) {
        return daoEquipos.getEquipoById(id);
    }


    public Integer addJugadorAEquipo(Jugador nuevoJugador) {
        return daoEquipos.addJugadorAEquipo(nuevoJugador);
    }
}
