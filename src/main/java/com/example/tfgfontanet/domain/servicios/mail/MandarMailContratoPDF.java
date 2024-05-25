package com.example.tfgfontanet.domain.servicios.mail;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.common.utiles.mail.MandarMail;
import com.example.tfgfontanet.common.utiles.pdfGenerator.PDFContratoGenerator;
import com.example.tfgfontanet.data.dao.DAOContratos;
import com.example.tfgfontanet.data.dao.DAOProfesionales;
import com.example.tfgfontanet.data.modelo.ContratoEntity;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import com.example.tfgfontanet.ui.errores.excepciones.MailException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MandarMailContratoPDF {

    private final DAOProfesionales daoProfesional;
    private final DAOContratos daoContratos;
    private final PDFContratoGenerator pdfContratoGenerator;
    private final MandarMail mandarMail;

    public void mandarMail(int id) {
        ContratoEntity contratoEntity = daoContratos.get(id).get();
        ProfesionalEntity profesionalEntity = daoProfesional.get(contratoEntity.getProfesional().getProfesionalId()).get();
        String correoProfesional = profesionalEntity.getUsuario().getCorreo();
        String mensaje = Constantes.CONTRATO_MSG1;

        try {
            pdfContratoGenerator.generarContrato(contratoEntity);
            File pdfFile = new File(Constantes.RUTA_CONTRATO + contratoEntity.getProfesional().getNombre() + Constantes.NOMBRE_PDF);
            mandarMail.generateAndSendEmailWithAttachment(correoProfesional, mensaje, Constantes.CONTRATO_SUBJECT, pdfFile);
        } catch (MessagingException e) {
            throw new MailException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
