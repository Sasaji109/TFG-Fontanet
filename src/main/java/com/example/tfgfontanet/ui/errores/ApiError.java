package com.example.tfgfontanet.ui.errores;

public class ApiError {

    private String mensaje;

    public ApiError(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
