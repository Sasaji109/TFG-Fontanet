package com.example.tfgfontanet.common.utiles.pdfGenerator;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import com.example.tfgfontanet.data.modelo.ContratoEntity;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import com.example.tfgfontanet.data.modelo.ServicioEntity;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class PDFContratoGenerator {

    public static void generarContrato(ContratoEntity contrato) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        ClienteEntity cliente = contrato.getCliente();
        ProfesionalEntity profesional = contrato.getProfesional();
        ServicioEntity servicio = contrato.getServicio();
        LocalDate fechaInicio = contrato.getFechaInicio();
        LocalDate fechaFin = contrato.getFechaFin();
        String estado = contrato.getEstado();

        PDDocumentInformation pdd = document.getDocumentInformation();
        pdd.setAuthor(cliente.getNombre() + Constantes.SPACE + cliente.getApellidos());
        pdd.setTitle(Constantes.CONTRATO_TRABAJO + profesional.getNombre() + Constantes.SPACE + profesional.getApellidos());
        pdd.setCreator(Constantes.CREATOR);
        pdd.setSubject(Constantes.SUBJECTCONTRATO);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 18);
        contentStream.setLeading(20);
        contentStream.newLineAtOffset(50, 750);

        contentStream.showText(Constantes.CONTRATO);
        contentStream.newLine();
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
        contentStream.showText(Constantes.DATOS_CLIENTE);
        contentStream.newLine();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.showText(Constantes.NOMBRE_APELLIDOS + cliente.getNombre() + Constantes.SPACE + cliente.getApellidos());
        contentStream.newLine();
        contentStream.showText(Constantes.NUMERO_TELEFONO + cliente.getNumero());
        contentStream.newLine();
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
        contentStream.showText(Constantes.DATOS_PROFESIONAL);
        contentStream.newLine();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.showText(Constantes.NOMBRE_APELLIDOS + profesional.getNombre() + Constantes.SPACE + profesional.getApellidos());
        contentStream.newLine();
        contentStream.showText(Constantes.NUMERO_TELEFONO + profesional.getNumero());
        contentStream.newLine();
        contentStream.showText(Constantes.OFICIOPDF + profesional.getOficio());
        contentStream.newLine();
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
        contentStream.showText(Constantes.DATOS_SERVICIO);
        contentStream.newLine();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.showText(Constantes.NOMBRE + servicio.getNombre());
        contentStream.newLine();
        contentStream.showText(Constantes.DESCRIPCION + servicio.getDescripcion());
        contentStream.newLine();
        contentStream.showText(Constantes.FECHA_INICIO_TRABAJO + fechaInicio.toString());
        contentStream.newLine();
        contentStream.showText(Constantes.FECHA_FIN_TRABAJO + (fechaFin != null ? fechaFin.toString() : Constantes.NO_ESPECIFICADA));
        contentStream.newLine();
        contentStream.showText(Constantes.TARIFA_BASE + servicio.getTarifaBase());
        contentStream.newLine();
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
        contentStream.showText(Constantes.ESTADOPDF);
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.newLine();
        contentStream.showText(estado);

        contentStream.endText();
        contentStream.close();

        document.save(new File(Constantes.RUTA_CONTRATO + Constantes.CONTRATO_PDF + contrato.getProfesional().getNombre() + Constantes.PDF));
        document.close();
    }
}

