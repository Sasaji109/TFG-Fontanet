package com.example.tfgfontanet.common.utiles.pdfGenerator;

import com.example.tfgfontanet.data.modelo.ClienteEntity;
import com.example.tfgfontanet.data.modelo.ContratoEntity;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import com.example.tfgfontanet.data.modelo.ServicioEntity;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class PDFContratoGenerator {

    public static void generarContrato(ContratoEntity contrato) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Obtener información del contrato
        ClienteEntity cliente = contrato.getCliente();
        ProfesionalEntity profesional = contrato.getProfesional();
        ServicioEntity servicio = contrato.getServicio();
        LocalDate fechaInicio = contrato.getFechaInicio();
        LocalDate fechaFin = contrato.getFechaFin();
        String estado = contrato.getEstado();

        PDDocumentInformation pdd = document.getDocumentInformation();
        pdd.setAuthor(cliente.getNombre() + " " + cliente.getApellidos());
        pdd.setTitle("Contrato de Trabajo para: " + profesional.getNombre() + " " + profesional.getApellidos());
        pdd.setCreator("Fontanet");
        pdd.setSubject("Contrato Laboral");

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText(); 
        
        contentStream.setFont(PDType1Font.TIMES_BOLD, 18); // Estilo de letra y tamaño
        contentStream.setLeading(20); // Espaciado entre líneas
        contentStream.newLineAtOffset(50, 750); // Posición inicial

        contentStream.showText("-- Contrato --");
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
        contentStream.showText("Datos del Servicio:");
        contentStream.newLine();
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12); // Restaurar estilo de letra normal
        contentStream.showText("Nombre: " + servicio.getNombre());
        contentStream.newLine();
        contentStream.showText("Descripción: " + servicio.getDescripcion());
        contentStream.newLine();
        contentStream.showText("Fecha de inicio del trabajo: " + fechaInicio.toString());
        contentStream.newLine();
        contentStream.showText("Fecha estimada de finalización: " + (fechaFin != null ? fechaFin.toString() : "No especificada"));
        contentStream.newLine();
        contentStream.showText("Tarifa Base: $" + servicio.getTarifaBase());
        contentStream.newLine();
        contentStream.newLine();
        
        contentStream.setFont(PDType1Font.TIMES_BOLD, 12); // Estilo de letra en negrita y tamaño
        contentStream.showText("Estado:");
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12); // Restaurar estilo de letra normal
        contentStream.newLine();
        contentStream.showText(estado);
        
        contentStream.endText();
        contentStream.close();

        document.save(new File("C:\\Users\\madrid\\IdeaProjects\\TFG-Fontanet\\contrato.pdf"));
        document.close();
        System.out.println("Contrato generado correctamente");
    }
}
