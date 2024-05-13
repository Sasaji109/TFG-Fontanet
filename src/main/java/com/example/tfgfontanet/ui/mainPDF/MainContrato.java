package com.example.tfgfontanet.ui.mainPDF;

import com.example.tfgfontanet.common.utiles.pdfGenerator.PDFContratoGenerator;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import com.example.tfgfontanet.data.modelo.ContratoEntity;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import com.example.tfgfontanet.data.modelo.ServicioEntity;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class MainContrato {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ProfesionalEntity profesional = new ProfesionalEntity(2, "Juan", "Pérez Cidón", "629483927", 6, "Fontanero", "Noche", 10, null);
        ClienteEntity cliente = new ClienteEntity(4, "Ana", "García Soria", "693749382", null, null);
        ServicioEntity servicio = new ServicioEntity(1, "Gotera", "Arreglar una gotera en el techo de la cocina", 100.50);

        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = LocalDate.now();
        ContratoEntity contrato = new ContratoEntity(1, cliente, profesional, servicio, fechaInicio, fechaFin, "pendiente");

        try {
            PDFContratoGenerator.generarContrato(contrato);
            
        } catch (IOException e) {
            System.out.println("Error al generar el contrato: " + e.getMessage());
            
        } finally {
            scanner.close();
        }
    }
}
