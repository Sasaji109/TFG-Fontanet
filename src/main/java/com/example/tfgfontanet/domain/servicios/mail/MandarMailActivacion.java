package com.example.tfgfontanet.domain.servicios.mail;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.common.utiles.mail.MandarMail;
import com.example.tfgfontanet.common.utiles.mail.RandomBytes;
import com.example.tfgfontanet.data.dao.DAOUsuario;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import com.example.tfgfontanet.domain.modelo.Usuario;
import com.example.tfgfontanet.domain.modelo.mapper.UsuarioEntityMapper;
import com.example.tfgfontanet.ui.errores.excepciones.MailException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MandarMailActivacion {

    private final DAOUsuario dao;
    private final UsuarioEntityMapper usuarioEntityMapper;
    private final MandarMail mandarMail;

    public void mandarMail(Usuario usuario) {
        UsuarioEntity usuarioEntity = dao.getUsuarioByCorreo(usuario.getCorreo());
        usuario = usuarioEntityMapper.toUsuario(usuarioEntity);
        String codigoActivacion = RandomBytes.randomBytes();
        usuario.setCodigoActivacion(codigoActivacion);
        usuario.setFechaEnvio(LocalDateTime.now());
        dao.updateUsuario(usuarioEntityMapper.toUsuarioEntity(usuario));

        try {
            mandarMail.generateAndSendEmail(usuario.getCorreo(), Constantes.MSG1 + codigoActivacion + Constantes.MSG2, Constantes.SUBJECT);
        } catch (MessagingException e) {
            throw new MailException(e.getMessage());
        }
    }
}
