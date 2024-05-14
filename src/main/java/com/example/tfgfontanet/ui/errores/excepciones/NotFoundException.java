package com.example.tfgfontanet.ui.errores.excepciones;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
       super(message);
    }
}
