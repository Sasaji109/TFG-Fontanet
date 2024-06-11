package com.example.tfgfontanet.domain.servicios.mail;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.common.utiles.mail.MandarMail;
import com.example.tfgfontanet.common.utiles.pdfGenerator.PDFFacturaGenerator;
import com.example.tfgfontanet.data.dao.DAOClientes;
import com.example.tfgfontanet.data.dao.DAOFacturas;
import com.example.tfgfontanet.data.dao.DAOProfesionales;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import com.example.tfgfontanet.data.modelo.FacturaEntity;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import com.example.tfgfontanet.ui.errores.excepciones.MailException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MandarMailFactura {

    private final DAOClientes daoClientes;
    private final DAOFacturas daoFacturas;
    private final DAOProfesionales daoProfesionales;
    private final PDFFacturaGenerator pdfFacturaGenerator;
    private final MandarMail mandarMail;

    public void mandarMailFacturaProfesionalPDF(int id) {
        FacturaEntity facturaEntity = daoFacturas.get(id).get();
        ProfesionalEntity profesionalEntity = daoProfesionales.get(facturaEntity.getProfesional().getProfesionalId()).get();
        String correoProfesional = profesionalEntity.getUsuario().getCorreo();
        String mensaje = Constantes.FACTURA_PROFESIONAL_PDF_MSG;

        try {
            File document = pdfFacturaGenerator.generarFactura(facturaEntity);
            mandarMail.generateAndSendEmailWithAttachment(correoProfesional, mensaje, Constantes.FACTURA_PROFESIONAL_PDF_SUBJECT, document);
        } catch (MessagingException | IOException e) {
            throw new MailException(e.getMessage());
        }
    }

    public void mandarMailFacturaClientePDF(int id) {
        FacturaEntity facturaEntity = daoFacturas.get(id).get();
        ClienteEntity clienteEntity = daoClientes.get(facturaEntity.getCliente().getClienteId()).get();
        String correoCliente = clienteEntity.getUsuario().getCorreo();
        String mensaje = Constantes.FACTURA_CLIENTE_PDF_MSG;

        try {
            File document = pdfFacturaGenerator.generarFactura(facturaEntity);
            mandarMail.generateAndSendEmailWithAttachment(correoCliente, mensaje, Constantes.FACTURA_CLIENTE_PDF_SUBJECT, document);
        } catch (MessagingException | IOException e) {
            throw new MailException(e.getMessage());
        }
    }

    public void mandarMailAvisoFacturaPagada(int id) {
        FacturaEntity facturaEntity = daoFacturas.get(id).get();
        ProfesionalEntity profesionalEntity = daoProfesionales.get(facturaEntity.getProfesional().getProfesionalId()).get();
        String correoProf = profesionalEntity.getUsuario().getCorreo();
        String mensaje = Constantes.FACTURA_PROFESIONAL_PAGADA_MSG;
        String subject = Constantes.FACTURA_PROFESIONAL_PAGADA_SUBJECT;

        try {
            mandarMail.generateAndSendEmail(correoProf, mensaje, subject);
        } catch (MessagingException e) {
            throw new MailException(e.getMessage());
        }
    }
}
