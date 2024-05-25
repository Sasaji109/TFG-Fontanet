package com.example.tfgfontanet.common;

import lombok.RequiredArgsConstructor;

public class Constantes {

    //Valores generales
    public static final String USER = "user";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String SERVER = "server";
    public static final String SPACE = " ";
    public static final String PROPERTIES = "config/config.properties";
    public static final String SPRING = "SPRING";
    public static final String TOKEN = "token";
    public static final String PROFESIONAL_ID = "profesionalId";
    public static final String CONTRATO_ID = "contratoId";
    public static final String FACTURA_ID = "facturaId";
    public static final String ESTADO = "estado";


    //Roles
    public static final String ROLE = "role";
    public static final String ADMIN = "ADMIN";
    public static final String PROFESIONAL = "PROF";
    public static final String CLIENTE = "CLIENTE";


    //Tokens
    public static final String BEARER = "Bearer";
    public static final String BEARERHEADER = "Bearer ";
    public static final int ACCESS_TOKEN_TIME = 600;
    public static final int REFRESH_TOKEN_TIME = 600;
    public static final String ERROR_AL_RENOVAR_EL_TOKEN = "Error al renovar el token: ";


    //KeyStore
    public static final String RSA = "RSA";
    public static final String CN_SERVIDOR = "CN=Servidor";
    public static final String PKCS_12 = "PKCS12";
    public static final String KEYSTORE_PFX = "src/main/resources/keystore.pfx";
    public static final String SHA_256_WITH_RSAENCRYPTION = "SHA256WithRSAEncryption";
    public static final String KEYSTORE_PASSWORD = "quevedo2dam";
    public static final int KEYSIZE = 2048;
    public static final int DAYS_TO_ADD = 365;
    public static final int VAL = 1;


    //Mongo Configuracion
    public static final String DATABASE = "mongodb://informatica.iesquevedo.es:2323";
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
    public static final String ENVIADO_UN_CORREO_DE_ACTIVACION = "El usuario no está activado. Se ha enviado un correo de activación.";


    //Mandar Mail Contrato
    public static final String CONTRATO_MSG1 = "Te han ofrecido un nuevo contrato. Por favor, acceda a la aplicación para revisarlo, y si está conforme, aceptarlo.";
    public static final String CONTRATO_SUBJECT = "Nuevo Contrato Ofrecido";


    //PDF Generator Contrato
    public static final String CONTRATO_TRABAJO = "Contrato de Trabajo para: ";
    public static final String CREATOR = "Fontanet";
    public static final String SUBJECTCONTRATO = "Contrato Laboral";
    public static final String CONTRATO = "-- Contrato --";
    public static final String DATOS_CLIENTE = "Datos del Cliente:";
    public static final String NOMBRE_APELLIDOS = "Nombre y apellidos: ";
    public static final String NUMERO_TELEFONO = "Número de teléfono: ";
    public static final String DATOS_PROFESIONAL = "Datos del Profesional:";
    public static final String OFICIO = "Oficio: ";
    public static final String DATOS_SERVICIO = "Datos del Servicio:";
    public static final String NOMBRE = "Nombre: ";
    public static final String DESCRIPCION = "Descripción: ";
    public static final String FECHA_INICIO_TRABAJO = "Fecha de inicio del trabajo: ";
    public static final String FECHA_FIN_TRABAJO = "Fecha estimada de finalización: ";
    public static final String NO_ESPECIFICADA = "No especificada";
    public static final String TARIFA_BASE = "Tarifa Base: $";
    public static final String ESTADOPDF = "Estado:";
    public static final String RUTA_CONTRATO = "C:\\Users\\madrid\\IdeaProjects\\TFG-Fontanet\\";
    public static final String NOMBRE_PDF = ".pdf";


    //PDF Generator Factura
    public static final String FACTURA_TRABAJO = "Factura de Trabajo para: ";
    public static final String SUBJECT_FACTURA = "Factura";
    public static final String FACTURA = "-- Factura --";
    public static final String MATERIALES = "Materiales:";
    public static final String PRECIO = ": $";
    public static final String X = " x ";
    public static final String PRECIO_TOTAL = "Precio Total: $";
    public static final String RUTA_FACTURA = "C:\\Users\\madrid\\IdeaProjects\\TFG-Fontanet\\factura.pdf";


    //Avisos de Registro, Activacion y Autenticacion
    public static final String EL_REGISTRO_NO_PUDO_COMPLETARSE = "El registro no pudo completarse";
    public static final String TIEMPO_ACTIVACION_EXPIRADO = "El tiempo de activación ha expirado";
    public static final String CODIGO_NO_ENCONTRADO = "Código de activación no encontrado";
    public static final String EL_REGISTRO_DEL_CLIENTE_FUE_EXITOSO = "El registro del cliente fue exitoso";
    public static final String EL_REGISTRO_DEL_PROFESIONAL_FUE_EXITOSO = "El registro del profesional fue exitoso.";



    //Alertas & Errores
    public static final String SQL_ERROR = "SQL error: ";
    public static final String MONGO_ERROR = "Mongo error: ";


    public static final String CLIENTE_O_PROFESIONAL_NOT_FOUND = "Cliente o Profesional no encontrado";
    public static final String CLIENTE_NOT_FOUND = "Cliente no encontrado";
    public static final String CLIENTES_NOT_FOUND = "Clientes no encontrados";
    public static final String CLIENTE_NO_ELIMINADO = "Cliente no eliminado";
    public static final String CLIENTE_NO_ACTUALIZADO = "Cliente no actualizado";


    public static final String FAVORITOS_NOT_FOUND = "Favoritos no encontrados";
    public static final String FAVORITO_NO_ELIMINADO = "Favorito no eliminado";
    public static final String FAVORITO_ANADIDO_EXITOSAMENTE = "Favorito añadido exitosamente";
    public static final String FAVORITO_NO_ANADIDO = "Favorito no añadido";


    public static final String PROFESIONAL_NOT_FOUND = "Profesional no encontrado";
    public static final String PROFESIONALES_NOT_FOUND = "Profesionales no encontrados";
    public static final String PROFESIONALES_NO_ENCONTRADOS_POR_EXPERIENCIA = "Profesionales no encontrados por experiencia";
    public static final String PROFESIONALES_NO_ENCONTRADOS_POR_OFICIO = "Profesionales no encontrados por oficio";
    public static final String PROFESIONALES_NO_ENCONTRADOS_POR_DISPONIBILIDAD = "Profesionales no encontrados por disponibilidad";
    public static final String PROFESIONALES_NO_ENCONTRADOS_POR_VALORACION = "Profesionales no encontrados por valoración";
    public static final String PROFESIONAL_NO_ELIMINADO = "Profesional no eliminado";
    public static final String PROFESIONAL_NO_ACTUALIZADO = "Profesional no actualizado";


    public static final String CONTRATOS_NO_ENCONTRADOS_PARA_EL_CLIENTE = "Contratos no encontrados para el cliente";
    public static final String CONTRATOS_NO_ENCONTRADOS_PARA_EL_PROFESIONAL = "Contratos no encontrados para el profesional";
    public static final String CONTRATOS_NO_ENCONTRADOS_PARA_EL_ESTADO_ESPECIFICADO = "Contratos no encontrados para el estado especificado";
    public static final String CONTRATO_NOT_FOUND = "Contrato no encontrado";
    public static final String CONTRATO_ANADIDO_EXITOSAMENTE = "Contrato añadido exitosamente";
    public static final String CONTRATO_NO_ANADIDO = "Contrato no añadido";
    public static final String CONTRATO_NO_ACTUALIZADO = "Contrato no actualizado";
    public static final String ESTADO_DEL_CONTRATO_NO_ACTUALIZADO = "Estado del contrato no actualizado";


    public static final String FACTURAS_NOT_FOUND = "Facturas no encontradas";
    public static final String FACTURAS_NO_ENCONTRADAS_PARA_EL_CLIENTE = "Facturas no encontradas para el cliente";
    public static final String FACTURAS_NO_ENCONTRADAS_PARA_EL_PROFESIONAL = "Facturas no encontradas para el profesional";
    public static final String FACTURA_NOT_FOUND = "Factura no encontrada";
    public static final String FACTURA_ANADIDA_EXITOSAMENTE = "Factura añadida exitosamente";
    public static final String FACTURA_NO_ANADIDA = "Factura no añadida";
    public static final String ESTADO_DE_LA_FACTURA_NO_ACTUALIZADO = "Estado de la factura no actualizado";
    public static final String FACTURA_O_MATERIAL_NOT_FOUND = "Factura o Material no encontrado";
    public static final String FACTURA_DE_MATERIAL_ANADIDA_EXITOSAMENTE = "Factura de material añadida exitosamente";
    public static final String FACTURA_DE_MATERIAL_NO_ANADIDA = "Factura de material no añadida";


    public static final String MATERIAL_NOT_FOUND = "Material no encontrado";
    public static final String MATERIALES_NOT_FOUND = "Materiales no encontrados";
    public static final String MATERIAL_NO_ELIMINADO = "Material no eliminado";
    public static final String MATERIAL_ANADIDO_EXITOSAMENTE = "Material añadido exitosamente";
    public static final String MATERIAL_NO_ANADIDO = "Material no añadido";
    public static final String MATERIAL_NO_ACTUALIZADO = "Material no actualizado";


    public static final String SERVICIO_NOT_FOUND = "Servicio no encontrado";
    public static final String SERVICIOS_NOT_FOUND = "Servicios no encontrados";
    public static final String SERVICIO_NO_ELIMINADO = "Servicio no eliminado";
    public static final String SERVICIO_ANADIDO_EXITOSAMENTE = "Servicio añadido exitosamente";
    public static final String SERVICIO_NO_ANADIDO = "Servicio no añadido";
    public static final String SERVICIO_NO_ACTUALIZADO = "Servicio no actualizado";


    public static final String FAVORITO_NOT_FOUND = "Favorito no encontrado";
    public static final String VALORACIONES_NOT_FOUND = "Valoraciones no encontradas";
    public static final String VALORACION_ANADIDA_EXITOSAMENTE = "Valoración añadida exitosamente";
    public static final String VALORACION_NO_ANADIDA = "Valoración no añadida";
    public static final String VALORACION_DEL_PROFESIONAL_NO_ACTUALIZADA = "Valoración del profesional no actualizada";
    public static final String USUARIO_NOT_FOUND = "Usuario no encontrado";


    //Scalars
    public static final String DOUBLE = "Double";
    public static final String DOUBLE_SCALAR = "Double scalar";
    public static final String LOCAL_DATE_TIME = "LocalDateTime";
    public static final String LOCAL_DATE_TIME_SCALAR = "LocalDateTime scalar";
    public static final String LOCAL_DATE = "LocalDate";
    public static final String LOCAL_DATE_SCALAR = "LocalDate scalar";


    //Paths
    public static final String WHITELIST_LOGIN = "/login/**";
    public static final String WHITELIST_REGISTRO = "/registro/**";
    public static final String LOGINPATH = "/login";
    public static final String REFRESH_PATH = "/refresh";
    public static final String REGISTRO_PATH = "/registro";
    public static final String CLIENTE_PATH = "/cliente";
   public static final String PROFESIONAL_PATH = "/profesional";
    public static final String CLIENTE_UPDATE_PATH = "/cliente/update";
    public static final String CONTRATO_ADD_PATH = "/contrato/add";
    public static final String CONTRATO_UPDATE_PATH = "/contrato/update";
    public static final String CONTRATO_UPDATE_ESTADO_PATH = "/contrato/updateEstado";
    public static final String FACTURAMATERIAL_ADD_PATH = "/facturaMaterial/add";
    public static final String FACTURA_ADD_PATH = "/factura/add";
    public static final String FACTURA_UPDATE_ESTADO_PATH = "/factura/updateEstado";
    public static final String FAVORITO_ADD_PATH = "/favoritos/add";
    public static final String MATERIAL_ADD_PATH = "/material/add";
    public static final String MATERIAL_UPDATE_PATH = "/material/update";
    public static final String SERVICIO_ADD_PATH = "/servicio/add";
    public static final String SERVICIO_UPDATE_PATH = "/servicio/update";
    public static final String VALORACION_PATH = "/valoracion";
    public static final String VALORACION_ADD_PATH = "/valoracion/add";
    public static final String PROFESIONAL_UPDATE_PATH = "/profesional/update";

    private Constantes(){}
}
