package com.example.tfgfontanet.ui.graphql;

import com.example.tfgfontanet.domain.modelo.Equipo;
import com.example.tfgfontanet.domain.modelo.Jugador;
import com.example.tfgfontanet.domain.servicios.EquiposService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class EquiposController { /*

    private final EquiposService equiposService;

    @QueryMapping
    public List<Equipo> getEquipos() {
        return equiposService.getAllEquipos();
    }

    @QueryMapping
    public Optional<Equipo> getEquipoById(@Argument Integer id) {
        return equiposService.getEquipoById(id);
    }

    @MutationMapping
    public Integer addJugadorToEquipo(@Argument Jugador nuevoJugador) {
        return equiposService.addJugadorAEquipo(nuevoJugador);
    } */
}
