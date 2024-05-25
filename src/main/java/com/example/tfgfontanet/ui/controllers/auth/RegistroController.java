package com.example.tfgfontanet.ui.controllers.auth;

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
@RequestMapping(Constantes.REGISTRO_PATH)
public class RegistroController {

    private final ClientesService clientesService;
    private final UsuariosService usuariosService;
    private final ProfesionalesService profesionalesService;
    private final MandarMailActivacion mandarMailActivacion;

    @Inject
    public RegistroController(ClientesService clientesService, UsuariosService usuariosService, ProfesionalesService profesionalesService, MandarMailActivacion mandarMailActivacion) {
        this.clientesService = clientesService;
        this.usuariosService = usuariosService;
        this.profesionalesService = profesionalesService;
        this.mandarMailActivacion = mandarMailActivacion;
    }

    @PostMapping(Constantes.CLIENTE_PATH)
    public String registroCliente(@RequestBody Cliente cliente) {
        if (Boolean.TRUE.equals(clientesService.registroCliente(cliente))) {
            mandarMailActivacion.mandarMail(cliente.getUsuario());
            return Constantes.EL_REGISTRO_DEL_CLIENTE_FUE_EXITOSO;
        } else {
            return Constantes.EL_REGISTRO_NO_PUDO_COMPLETARSE;
        }
    }

    @PostMapping(Constantes.PROFESIONAL_PATH)
    public String registroProfesional(@RequestBody Profesional profesional) {
        if (Boolean.TRUE.equals(profesionalesService.registroProfesional(profesional))) {
            mandarMailActivacion.mandarMail(profesional.getUsuario());
            return Constantes.EL_REGISTRO_DEL_PROFESIONAL_FUE_EXITOSO;
        } else {
            return Constantes.EL_REGISTRO_NO_PUDO_COMPLETARSE;
        }
    }

    @GetMapping(Constantes.ACTIVACION_PATH)
    public String activacionUsuario(@RequestParam(Constantes.ACTIVACION_CODIGO) String codigoActivacion) {
        return usuariosService.activarUsuario(codigoActivacion);
    }
}
