package com.example.tfgfontanet.domain.servicios.mail;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.common.utiles.mail.MandarMail;
import com.example.tfgfontanet.common.utiles.pdfGenerator.PDFContratoGenerator;
import com.example.tfgfontanet.data.dao.DAOClientes;
import com.example.tfgfontanet.data.dao.DAOContratos;
import com.example.tfgfontanet.data.dao.DAOProfesionales;
import com.example.tfgfontanet.data.modelo.ContratoEntity;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import com.example.tfgfontanet.ui.errores.excepciones.MailException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MandarMailContrato {

    private final DAOClientes daoClientes;
    private final DAOContratos daoContratos;
    private final DAOProfesionales daoProfesionales;
    private final PDFContratoGenerator pdfContratoGenerator;
    private final MandarMail mandarMail;

    public void mandarMailContratoProfesionalPDF(int id) {
        ContratoEntity contratoEntity = daoContratos.get(id).get();
        ProfesionalEntity profesionalEntity = daoProfesionales.get(contratoEntity.getProfesional().getProfesionalId()).get();
        String correoProfesional = profesionalEntity.getUsuario().getCorreo();
        String mensaje = Constantes.CONTRATO_PROFESIONAL_PDF_MSG;

        try {
            pdfContratoGenerator.generarContrato(contratoEntity);
            File pdfFile = new File(Constantes.RUTA_CONTRATO + contratoEntity.getProfesional().getNombre() + Constantes.NOMBRE_PDF);
            mandarMail.generateAndSendEmailWithAttachment(correoProfesional, mensaje, Constantes.CONTRATO_PROFESIONAL_PDF_SUBJECT, pdfFile);
        } catch (MessagingException | IOException e) {
            throw new MailException(e.getMessage());
        }
    }

    public void mandarMailContratoClientePDF(int id) {
        ContratoEntity contratoEntity = daoContratos.get(id).get();
        ClienteEntity clienteEntity = daoClientes.get(contratoEntity.getCliente().getClienteId()).get();
        String correoCliente = clienteEntity.getUsuario().getCorreo();
        String mensaje = Constantes.CONTRATO_CLIENTE_PDF_MSG;

        try {
            pdfContratoGenerator.generarContrato(contratoEntity);
            File pdfFile = new File(Constantes.RUTA_CONTRATO + contratoEntity.getProfesional().getNombre() + Constantes.NOMBRE_PDF);
            mandarMail.generateAndSendEmailWithAttachment(correoCliente, mensaje, Constantes.CONTRATO_CLIENTE_PDF_SUBJECT, pdfFile);
        } catch (MessagingException | IOException e) {
            throw new MailException(e.getMessage());
        }
    }

    public void mandarMailAvisoContratoVigente(int id) {
        ContratoEntity contratoEntity = daoContratos.get(id).get();
        ProfesionalEntity profesionalEntity = daoProfesionales.get(contratoEntity.getProfesional().getProfesionalId()).get();
        String correoProf = profesionalEntity.getUsuario().getCorreo();
        String mensaje = Constantes.CONTRATO_PROFESIONAL_VIGENTE_MSG;
        String subject = Constantes.CONTRATO_PROFESIONAL_VIGENTE_SUBJECT;

        try {
            mandarMail.generateAndSendEmail(correoProf, mensaje, subject);
        } catch (MessagingException e) {
            throw new MailException(e.getMessage());
        }
    }
}
