package com.example.tfgfontanet.data.repositorios;

import com.example.tfgfontanet.domain.modelo.Equipo;
import com.example.tfgfontanet.domain.modelo.Jugador;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class DAOEquipos { /*

    public List<Equipo> getAllEquipos() {
        return BD.equipos;
    }

    public Optional<Equipo> getEquipoById(Integer id) {
        return BD.equipos.stream()
                .filter(equipo -> equipo.getId().equals(id))
                .findFirst();
    }

    public Integer addJugadorAEquipo(Jugador newJugador) {
        Optional<Equipo> equipoOptional = getEquipoById(newJugador.getEquipoId());
        if (equipoOptional.isPresent()) {
            Equipo equipo = equipoOptional.get();
            BD.equipos.remove(equipo);
            equipo.getJugadores().add(newJugador);
            BD.equipos.add(equipo);
            return 1;
        }
        return 0;
    } */
}
