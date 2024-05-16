package com.example.tfgfontanet.ui.errores.excepciones;

public class CRUDException extends RuntimeException {
    public CRUDException(String message) {
       super(message);
    }
}
