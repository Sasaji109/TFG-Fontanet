package com.example.tfgfontanet.common.utiles.pdfGenerator;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.data.modelo.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PDFFacturaGenerator {

    public static void generarFactura(FacturaEntity factura) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        ClienteEntity cliente = factura.getCliente();
        ProfesionalEntity profesional = factura.getProfesional();
        ServicioEntity servicio = factura.getServicio();
        List<FacturaMaterialEntity> facturaMaterialList = factura.getMateriales();
        String estado = factura.getEstado();

        PDDocumentInformation pdd = document.getDocumentInformation();
        pdd.setAuthor(profesional.getNombre() + " " + profesional.getApellidos());
        pdd.setTitle(Constantes.FACTURA_TRABAJO + cliente.getNombre() + " " + cliente.getApellidos());
        pdd.setCreator(Constantes.CREATOR);
        pdd.setSubject(Constantes.SUBJECT_FACTURA);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 18);
        contentStream.setLeading(20);
        contentStream.newLineAtOffset(50, 750);

        contentStream.showText(Constantes.FACTURA);
        contentStream.newLine();
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
        contentStream.showText(Constantes.DATOS_PROFESIONAL);
        contentStream.newLine();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.showText(Constantes.NOMBRE_APELLIDOS + profesional.getNombre() + " " + profesional.getApellidos());
        contentStream.newLine();
        contentStream.showText(Constantes.NUMERO_TELEFONO + profesional.getNumero());
        contentStream.newLine();
        contentStream.showText(Constantes.OFICIO + profesional.getOficio());
        contentStream.newLine();
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
        contentStream.showText(Constantes.DATOS_CLIENTE);
        contentStream.newLine();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.showText(Constantes.NOMBRE_APELLIDOS + cliente.getNombre() + " " + cliente.getApellidos());
        contentStream.newLine();
        contentStream.showText(Constantes.NUMERO_TELEFONO + cliente.getNumero());
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
        contentStream.showText(Constantes.TARIFA_BASE + servicio.getTarifaBase());
        contentStream.newLine();
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
        contentStream.showText(Constantes.MATERIALES);
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.newLine();
        for (FacturaMaterialEntity facturaMaterial : facturaMaterialList) {
            MaterialEntity material = facturaMaterial.getMaterial();
            contentStream.showText(material.getNombre() + Constantes.PRECIO + material.getPrecio() + Constantes.X + facturaMaterial.getCantidad());
            contentStream.newLine();
        }
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
        contentStream.showText(Constantes.PRECIO_TOTAL + factura.getPrecio());
        contentStream.newLine();
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
        contentStream.showText(Constantes.ESTADOPDF);
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
        contentStream.newLine();
        contentStream.showText(estado);

        contentStream.endText();
        contentStream.close();

        document.save(new File(Constantes.RUTA_FACTURA));
        document.close();
    }
}
