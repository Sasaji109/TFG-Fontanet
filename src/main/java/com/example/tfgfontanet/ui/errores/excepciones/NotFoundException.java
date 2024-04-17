package com.example.tfgfontanet.ui.errores.excepciones;

import com.example.tfgfontanet.common.Constantes;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
       super(Constantes.NO_SE_ENCONTRO);
    }
}