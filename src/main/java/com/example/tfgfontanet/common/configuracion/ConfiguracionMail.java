package com.example.tfgfontanet.common.configuracion;

import com.example.tfgfontanet.common.Constantes;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;
import java.util.Properties;

@Getter
@Log4j2
@Singleton
public class ConfiguracionMail {

    private String MAIL_SMTP_PORT;
    private String MAIL_SMTP_AUTH;
    private String MAIL_SMTP_SSL_TRUST;
    private String MAIL_SMTP_STARTTLS_ENABLE;
    private String MAIL_HOST;
    private String MAIL_USERNAME;
    private String MAIL_PASSWORD;

    public ConfiguracionMail() {
        try {
            Properties p = new Properties();
            p.load(ConfiguracionMail.class.getClassLoader().getResourceAsStream(Constantes.PROPERTIES));
            this.MAIL_SMTP_PORT = p.getProperty(Constantes.MAIL_SMTP_PORT);
            this.MAIL_SMTP_AUTH = p.getProperty(Constantes.MAIL_SMTP_AUTH);
            this.MAIL_SMTP_SSL_TRUST = p.getProperty(Constantes.MAIL_SMTP_SSL_TRUST);
            this.MAIL_SMTP_STARTTLS_ENABLE = p.getProperty(Constantes.MAIL_SMTP_STARTTLS_ENABLE);
            this.MAIL_HOST = p.getProperty(Constantes.MAIL_HOST);
            this.MAIL_USERNAME = p.getProperty(Constantes.MAIL_USERNAME);
            this.MAIL_PASSWORD = p.getProperty(Constantes.MAIL_PASSWORD);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


