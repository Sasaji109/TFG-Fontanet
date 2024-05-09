package com.example.tfgfontanet.ui.main.pdfs;

import com.example.tfgfontanet.common.utiles.pdfGenerator.PDFFacturaGenerator;
import com.example.tfgfontanet.data.modelo.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainFactura {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ProfesionalEntity profesional = new ProfesionalEntity(2, "Juan", "Pérez Cidón", "629483927", 6, "Fontanero", "Noche", 10, null);
        ClienteEntity cliente = new ClienteEntity(4, "Ana", "García Soria", "693749382", null, null);
        ServicioEntity servicio = new ServicioEntity(1, "Gotera", "Arreglar una gotera en el techo de la cocina", 100.50);

        List<FacturaMaterialEntity> materiales = new ArrayList<>();
        MaterialEntity material1 = new MaterialEntity(1, "Tuberías", "Tuberías de PVC", 20.0);
        materiales.add(new FacturaMaterialEntity(1,2, material1, 3));
        MaterialEntity material2 = new MaterialEntity(2, "Pegamento", "Pegamento para PVC", 5.0);
        materiales.add(new FacturaMaterialEntity(2,2, material2, 1));

        FacturaEntity factura = new FacturaEntity();
        factura.setCliente(cliente);
        factura.setProfesional(profesional);
        factura.setServicio(servicio);
        factura.setMateriales(materiales);
        factura.setPrecio(calcularPrecioTotal(servicio, materiales)); // Calcular precio total
        factura.setEstado("pendiente");

        try {
            PDFFacturaGenerator.generarFactura(factura);

        } catch (IOException e) {
            System.out.println("Error al generar la factura: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }

    private static Double calcularPrecioTotal(ServicioEntity servicio, List<FacturaMaterialEntity> materiales) {
        Double precioTotal = servicio.getTarifaBase();
        for (FacturaMaterialEntity facturaMaterial : materiales) {
            precioTotal += facturaMaterial.getCantidad() * facturaMaterial.getMaterial().getPrecio();
        }
        return precioTotal;
    }
}
