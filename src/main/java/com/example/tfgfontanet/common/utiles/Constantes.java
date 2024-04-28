package com.example.tfgfontanet.common.utiles;

public class Constantes {


    //Valores generales
    public static final String USER = "user";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String SERVER = "server";
    public static final String SPACE = " ";


    //Roles
    public static final String ROLE = "role";
    public static final String ADMIN = "ADMIN";
    public static final String PROFESIONAL = "PROF";
    public static final String CLIENTE = "CLIENT";


    //Tokens
    public static final String BEARER = "Bearer ";
    public static final int ACCESS_TOKEN_TIME = 4200;
    public static final int ACCESS_TOKEN_EXPIRATION_TIME_SECONDS = 4800;
    public static final int REFRESH_TOKEN_TIME = 4200;
    public static final String ERROR_AL_GENERAR_EL_TOKEN = "Error al generar el token: ";
    public static final String ERROR_AL_RENOVAR_EL_TOKEN = "Error al renovar el token: ";


    //KeyStore
    public static final String RSA = "RSA";
    public static final String CN_SERVIDOR = "CN=Servidor";
    public static final String PKCS_12 = "PKCS12";
    public static final String KEYSTORE_PFX = "src/main/resources/keystore.pfx";
    public static final String SHA_256_WITH_RSAENCRYPTION = "SHA256WithRSAEncryption";
    public static final String CONTENT_SIGNER_BUILDER = "SHA256WithRSA";
    public static final String KEYSTORE_PASSWORD = "quevedo2dam";
    public static final String ERROR_CARGA_PRIVATEKEY_KEYSTORE = "Error al cargar la clave privada desde el keystore: ";
    public static final int KEYSIZE = 2048;
    public static final int DAYS_TO_ADD = 365;
    public static final int VAL = 1;

    //Mongo Configuration
    public static final String DATABASE_HOST = "mongodb://informatica.iesquevedo.es:";
    public static final String DATABASE_PORT = "2323";
    public static final String DATABASE_NAME = "samuelsanchez_tfg";


    //Paths
    public static final String WHITELIST_LOGIN = "/login/**";
    public static final String LOGINPATH = "/login";
    public static final String EMPLEADOSPATH = "/api/empleados";
    public static final String MOVILESPATH = "/api/moviles";
    public static final String ADDPATH = "/add";
    public static final String UPDATEPATH = "/update";
    public static final String DELETEPATH = "/delete";


    //Avisos y errores
    public static final String NO_SE_ENCONTRO = "no se encontro";

    public static final String ERROR_REGISTRO = "Error al hacer el registro";
    public static final String USER_NOT_FOUND = "Usuario no encontrado";
    public static final String USERNAME_NOT_FOUND = "Usuario no encontrado con ese nombre: ";

    private Constantes(){}
}
