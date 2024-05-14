package com.example.tfgfontanet.ui.rest;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.Cliente;
import com.example.tfgfontanet.domain.modelo.Profesional;
import com.example.tfgfontanet.domain.servicios.ClientesService;
import com.example.tfgfontanet.domain.servicios.ProfesionalesService;
import com.example.tfgfontanet.domain.servicios.auth.UsuariosService;
import com.example.tfgfontanet.domain.servicios.mail.MandarMailActivacion;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/registro")
public class RestRegistro {

    private final ClientesService clientesService;
    private final UsuariosService usuariosService;
    private final ProfesionalesService profesionalesService;
    private final MandarMailActivacion mandarMailActivacion;

    @Inject
    public RestRegistro(ClientesService clientesService, UsuariosService usuariosService, ProfesionalesService profesionalesService, MandarMailActivacion mandarMailActivacion) {
        this.clientesService = clientesService;
        this.usuariosService = usuariosService;
        this.profesionalesService = profesionalesService;
        this.mandarMailActivacion = mandarMailActivacion;
    }

    @PostMapping("/cliente")
    public String registroCliente(@RequestBody Cliente cliente) {
        if (Boolean.TRUE.equals(clientesService.registroCliente(cliente))) {
            mandarMailActivacion.mandarMail(cliente.getUsuario());
            return "El registro del cliente fue exitoso";
        } else {
            return Constantes.EL_REGISTRO_NO_PUDO_COMPLETARSE;
        }
    }

    @PostMapping("/profesional")
    public String registroProfesional(@RequestBody Profesional profesional) {
        if (Boolean.TRUE.equals(profesionalesService.registroProfesional(profesional))) {
            mandarMailActivacion.mandarMail(profesional.getUsuario());
            return "El registro del profesional fue exitoso.";
        } else {
            return Constantes.EL_REGISTRO_NO_PUDO_COMPLETARSE;
        }
    }

    @GetMapping(Constantes.ACTIVACION_PATH)
    public String activacionUsuario(@RequestParam(Constantes.ACTIVACION_CODIGO) String codigoActivacion) {
        return usuariosService.activarUsuario(codigoActivacion);
    }
}
