package com.example.tfgfontanet.common.utiles.pdfGenerator;

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

        // Obtener información del contrato
        ClienteEntity cliente = factura.getCliente();
        ProfesionalEntity profesional = factura.getProfesional();
        ServicioEntity servicio = factura.getServicio();
        List<FacturaMaterialEntity> facturaMaterialList = factura.getMateriales();
        String estado = factura.getEstado();

        PDDocumentInformation pdd = document.getDocumentInformation();
        pdd.setAuthor(profesional.getNombre() + " " + profesional.getApellidos());
        pdd.setTitle("Factura de Trabajo para: " + cliente.getNombre() + " " + cliente.getApellidos());
        pdd.setCreator("Fontanet");
        pdd.setSubject("Factura");

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText(); 
        
        contentStream.setFont(PDType1Font.TIMES_BOLD, 18); // Estilo de letra y tamaño
        contentStream.setLeading(20); // Espaciado entre líneas
        contentStream.newLineAtOffset(50, 750); // Posición inicial

        contentStream.showText("-- Factura --");
        contentStream.newLine();
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 12); // Estilo de letra en negrita y tamaño
        contentStream.showText("Datos del Profesional:");
        contentStream.newLine();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12); // Restaurar estilo de letra normal
        contentStream.showText("Nombre y apellidos: " + profesional.getNombre() + " " + profesional.getApellidos());
        contentStream.newLine();
        contentStream.showText("Número de teléfono: " + profesional.getNumero());
        contentStream.newLine();
        contentStream.showText("Oficio: " + profesional.getOficio());
        contentStream.newLine();
        contentStream.newLine();
        
        contentStream.setFont(PDType1Font.TIMES_BOLD, 12); // Estilo de letra en negrita y tamaño
        contentStream.showText("Datos del Cliente:");
        contentStream.newLine();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12); // Restaurar estilo de letra normal
        contentStream.showText("Nombre y apellidos: " + cliente.getNombre() + " " + cliente.getApellidos());
        contentStream.newLine();
        contentStream.showText("Número de teléfono: " + cliente.getNumero());
        contentStream.newLine();
        contentStream.newLine();
        
        contentStream.setFont(PDType1Font.TIMES_BOLD, 12); // Estilo de letra en negrita y tamaño
        contentStream.showText("Datos del Servicio:");
        contentStream.newLine();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12); // Restaurar estilo de letra normal
        contentStream.showText("Nombre: " + servicio.getNombre());
        contentStream.newLine();
        contentStream.showText("Descripción: " + servicio.getDescripcion());
        contentStream.newLine();
        contentStream.showText("Tarifa Base: $" + servicio.getTarifaBase());
        contentStream.newLine();
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 12); // Estilo de letra en negrita y tamaño
        contentStream.showText("Materiales:");
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12); // Restaurar estilo de letra normal
        contentStream.newLine();
        for (FacturaMaterialEntity facturaMaterial : facturaMaterialList) {
            MaterialEntity material = facturaMaterial.getMaterial();
            contentStream.showText(material.getNombre() + ": $" + material.getPrecio() + " x " + facturaMaterial.getCantidad());
            contentStream.newLine();
        }
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 12); // Estilo de letra en negrita y tamaño
        contentStream.showText("Precio Total: $" + factura.getPrecio());
        contentStream.newLine();
        contentStream.newLine();

        contentStream.setFont(PDType1Font.TIMES_BOLD, 12); // Estilo de letra en negrita y tamaño
        contentStream.showText("Estado:");
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12); // Restaurar estilo de letra normal
        contentStream.newLine();
        contentStream.showText(estado);
        
        contentStream.endText();
        contentStream.close();

        document.save(new File("C:\\Users\\madrid\\IdeaProjects\\TFG-Fontanet\\factura.pdf"));
        document.close();
        System.out.println("Factura generada correctamente");
    }
}
