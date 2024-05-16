package com.example.tfgfontanet.common;

public class Constantes {

    //Valores generales
    public static final String USER = "user";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String SERVER = "server";
    public static final String SPACE = " ";
    public static final String PROPERTIES = "config/config.properties";


    //Roles
    public static final String ROLE = "role";
    public static final String ADMIN = "ADMIN";
    public static final String PROFESIONAL = "PROF";
    public static final String CLIENTE = "CLIENT";


    //Tokens
    public static final String BEARER = "Bearer";
    public static final String BEARERHEADER = "Bearer ";
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


    //Mongo Configuracion
    public static final String DATABASE_HOST = "mongodb://informatica.iesquevedo.es:";
    public static final int DATABASE_PORT = 2323;
    public static final String DATABASE_NAME = "samuelsanchez_tfg";


    //Propiedades del correo
    public static final String MAIL_SMTP_PORT = "mail.smtp.port";
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String MAIL_SMTP_SSL_TRUST = "mail.smtp.ssl.trust";
    public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static final String CONTENT_TYPE = "text/html";
    public static final String PROTOCOL = "smtp";
    public static final String MAIL_HOST = "mail.host";
    public static final String MAIL_USERNAME = "mail.username";
    public static final String MAIL_PASSWORD = "mail.password";


    //Activacion Cuenta
    public static final String ACTIVACION_PATH = "/activacion";
    public static final String ACTIVACION_CODIGO = "codigo";
    public static final int TIEMPO_ACTIVACION_MINUTOS = 1;
    public static final String MSG1 = "<html><body>Pincha en el siguiente enlace para activar tu usuario: <a href=\"http://localhost:8080/registro/activacion?codigo=";
    public static final String MSG2 = "\">Activar usuario</a></body></html>";
    public static final String SUBJECT = "Activación del usuario";
    public static final String ACTIVADO = "Usuario activado";


    //Avisos de Registro, Activacion y Autenticacion
    public static final String EL_REGISTRO_NO_PUDO_COMPLETARSE = "El registro no pudo completarse";
    public static final String TIEMPO_ACTIVACION_EXPIRADO = "El tiempo de activación ha expirado";
    public static final String CODIGO_NO_ENCONTRADO = "Código de activación no encontrado";
    public static final String AUTENTICACION_CORRECTA = "Autenticación correcta";


    //Alertas & Errores DAO
    public static final String CUSTOMER_NOT_FOUND = "Customer not found";
    public static final String CREDENTIALS_NOT_FOUND = "CredentialsH not found";
    public static final String MENU_ITEM_NOT_FOUND = "MenuItemH not found";
    public static final String SQL_ERROR = "SQL error: ";
    public static final String MONGO_ERROR = "Mongo error: ";
    public static final String ERROR_ON_LOADING_CUSTOMERS = "Error on loading customers";
    public static final String ERROR_ON_ADDING_THE_CUSTOMER = "Error on adding the customer";
    public static final String ERROR_ON_LOADING_CREDENTIALS = "Error on loading credentials";
    public static final String CREDENTIAL_NOT_FOUND = "Credential not found";
    public static final String ERROR_ON_CREATING_CREDENTIALS_FOR_THE_CUSTOMER = "Error on creating credentials for the customer";
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
    public static final String ERROR_ON_LOADING_ORDERS = "Error on loading orders";
    public static final String ERROR_ON_ADDING_THE_ORDER = "Error on adding the order";
    public static final String ORDER_NOT_FOUND = "Order not found";
    public static final String ERROR_ON_ADDING_THE_ORDER_ITEM = "Error on adding the order item";
    public static final String ORDER_ITEM_NOT_FOUND = "Order item not found";
    public static final String ERROR_ON_RETRIEVING_THE_ORDER_ITEM = "Error on retrieving the order item";
    public static final String ERROR_ON_LOADING_MENU_ITEMS = "Error on loading menuItems";
    public static final String MENU_ITEM_NOT_FOUND_FOR_NAME = "MenuItem not found for name: ";
    public static final String ERROR_ON_LOADING_TABLES = "Error on loading tables";


    //Paths
    public static final String WHITELIST_LOGIN = "/login/**";
    public static final String WHITELIST_REGISTRO = "/registro/**";
    public static final String LOGINPATH = "/login";
    public static final String EMPLEADOSPATH = "/api/empleados";
    public static final String MOVILESPATH = "/api/moviles";
    public static final String ADDPATH = "/add";
    public static final String UPDATEPATH = "/update";
    public static final String DELETEPATH = "/delete";


    //Avisos & errores Registro
    public static final String NO_SE_ENCONTRO = "no se encontro";

    public static final String ERROR_REGISTRO = "Error al hacer el registro";
    public static final String USER_NOT_FOUND = "Usuario no encontrado";
    public static final String USERNAME_NOT_FOUND = "Usuario no encontrado con ese nombre: ";


    //Avisos y errores borrar
    public static final String MOVIL_NOT_FOUND = "Movil no encontrado en ese empleado";
    public static final String EMPLEADO_NOT_FOUND = "Empleado no encontrado";
    public static final String DELETE_MOVIL_SUCCESS = "Movil eliminado exitósamente";
    public static final String DELETE_MOVIL_ERROR = "Error al eliminar el movil: ";
    public static final String UPDATE_MOVIL_SUCCESS = "Movil actualizado exitósamente";
    public static final String UPDATE_MOVIL_ERROR = "Error al actualizar el movil: ";
    public static final String ADD_EMPLEADO_SUCCESS = "Empleado añadido exitósamente";
    public static final String ADD_EMPLEADO_ERROR = "Error al añadir el nuevo empleado: ";

    public static final String ADD_MOVIL_SUCCESS = "Movil añadido exitósamente";
    public static final String ADD_MOVIL_ERROR = "Error al añadir el nuevo movil: ";

    private Constantes(){}
}