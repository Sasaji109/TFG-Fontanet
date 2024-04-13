package com.example.tfgfontanet.common;

public class Constantes {


    //LOGIN
    public static final String USER = "user";
    public static final String ROLE = "role";
    public static final String BEARER = "Bearer ";
    public static final String ADMIN = "admin";
    public static final int ACCESS_TOKEN_TIME = 4200;
    public static final int ACCESS_TOKEN_EXPIRATION_TIME_SECONDS = 4800;
    public static final int REFRESH_TOKEN_TIME = 4200;
    public static final String ERROR_AL_GENERAR_EL_TOKEN = "Error al generar el token: ";
    public static final String ERROR_AL_RENOVAR_EL_TOKEN = "Error al renovar el token: ";


    //KeyStore
    public static final String RSA = "RSA";
    public static final String CN_SERVIDOR = "CN=Servidor";
    public static final String SERVER = "server";
    public static final String PKCS_12 = "PKCS12";
    public static final String KEYSTORE_PFX = "src/main/resources/keystore.pfx";
    public static final String CONTENT_SIGNER_BUILDER = "SHA256WithRSA";
    public static final String KEYSTORE_PASSWORD = "quevedo2dam";
    public static final String ERROR_CARGA_PRIVATEKEY_KEYSTORE = "Error al cargar la clave privada desde el keystore: ";


    //Avisos y errores
    public static final String NO_SE_ENCONTRO = "no se encontro";

    public static final String ESTUDIOS_NO_ENCONTRADOS = "Estudios no encontrados";
    public static final String ESTUDIO_NOT_FOUND = "Estudio no encontrado";
    public static final String ERROR_GET_ESTUDIO = "Error al traer el Estudio";

    public static final String GENEROS_NO_ENCONTRADOS = "Generos no encontrados";
    public static final String GENERO_NOT_FOUND = "Genero no encontrado";
    public static final String ERROR_GET_GENERO = "Error al traer el Genero";

    public static final String DIRECTORES_NO_ENCONTRADOS = "Directores no encontrados";
    public static final String DIRECTOR_NOT_FOUND = "Director no encontrado";
    public static final String ERROR_GET_DIRECTOR = "Error al traer el Director";


    public static final String ACTORES_NO_ENCONTRADOS = "Actores no encontrados";
    public static final String ACTOR_NOT_FOUND = "Actor no encontrado";
    public static final String ERROR_GET_ACTOR = "Error al traer el Actor";
    public static final String ERROR_ADD_ACTOR = "Error al añadir el actor";
    public static final String ERROR_DELETE_ACTOR = "Error al eliminar el actor";
    public static final String ERROR_UPDATE_ACTOR = "Error al actualizar el actor";

    public static final String PELICULAS_NO_ENCONTRADAS = "Peliculas no encontradas";
    public static final String PELICULA_NOT_FOUND = "Pelicula no encontrada";
    public static final String ERROR_GET_PELICULA = "Error al traer la Pelicula";
    public static final String ERROR_ADD_PELICULA = "Error al añadir la pelicula";
    public static final String ERROR_DELETE_PELICULA = "Error al eliminar la pelicula";
    public static final String ERROR_UPDATE_PELICULA = "Error al actualizar la pelicula";

    public static final String ERROR_REGISTRO = "Error al hacer el registro";
    public static final String USUARIO_NOT_FOUND = "Usuario no encontrado: ";
    public static final String USERNAME_NOT_FOUND = "Usuario no encontrado con ese nombre: ";
    private Constantes(){}
}
