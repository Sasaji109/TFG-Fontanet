package com.example.tfgfontanet.data;

import com.example.tfgfontanet.domain.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BD {
    public static List<Usuario> usuarios = new ArrayList<>();
    private BD(){}

    static {
        //Contraseña: 1234
        Usuario usuario1 = new Usuario(1, "admin", "2000/10/23", "$2a$10$qFvk70IVphAh/q8O4.YCwOAiogSyK8ZLgwdbCX4pwI2bcBsKMfaCe", "ADMIN");

        usuarios.add(usuario1);
    }
}


