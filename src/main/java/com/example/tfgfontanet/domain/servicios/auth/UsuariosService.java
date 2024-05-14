package com.example.tfgfontanet.domain.servicios.auth;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.data.dao.DAOUsuario;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import com.example.tfgfontanet.domain.modelo.mapper.UsuarioEntityMapper;
import com.example.tfgfontanet.domain.modelo.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class UsuariosService {

    private final DAOUsuario dao;
    private final UsuarioEntityMapper usuarioEntityMapper;

    public String activarUsuario(String codigoActivacion) {
        UsuarioEntity usuarioEntity = dao.getUsuarioByCodigo(codigoActivacion);
        Usuario usuario = usuarioEntityMapper.toUsuario(usuarioEntity);

        if (usuario != null) {
            if (tiempoTranscurrido(usuario.getFechaEnvio())) {
                usuario.setActivado(true);
                dao.updateUsuario(usuarioEntityMapper.toUsuarioEntity(usuario));
                return Constantes.ACTIVADO;
            } else {
                return Constantes.TIEMPO_ACTIVACION_EXPIRADO;
            }
        } else {
            return Constantes.CODIGO_NO_ENCONTRADO;
        }
    }

    private boolean tiempoTranscurrido(LocalDateTime fechaEnvio) {
        LocalDateTime ahora = LocalDateTime.now();
        long minutosDiferencia = ChronoUnit.MINUTES.between(fechaEnvio, ahora);
        return minutosDiferencia <= Constantes.TIEMPO_ACTIVACION_MINUTOS;
    }
}
