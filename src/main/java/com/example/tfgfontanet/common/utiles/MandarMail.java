package com.example.tfgfontanet.common.utiles;

import com.example.tfgfontanet.common.configuracion.ConfiguracionMail;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import java.util.Properties;

@Log4j2
@Singleton
public class MandarMail {

    private ConfiguracionMail config;
    @Inject
    public MandarMail(ConfiguracionMail config) {
        this.config = config;
    }

    public void generateAndSendEmail(String to, String msg, String subject) throws MessagingException {
        Properties mailServerProperties = new Properties();
        mailServerProperties.put(Constantes.MAIL_SMTP_PORT, config.getMAIL_SMTP_PORT());
        mailServerProperties.put(Constantes.MAIL_SMTP_AUTH, config.getMAIL_SMTP_AUTH());
        mailServerProperties.put(Constantes.MAIL_SMTP_SSL_TRUST, config.getMAIL_SMTP_SSL_TRUST());
        mailServerProperties.put(Constantes.MAIL_SMTP_STARTTLS_ENABLE, config.getMAIL_SMTP_STARTTLS_ENABLE());

        Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        MimeMessage generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        generateMailMessage.setSubject(subject);
        String emailBody = msg;
        generateMailMessage.setContent(emailBody, Constantes.CONTENT_TYPE);

        Transport transport = getMailSession.getTransport(Constantes.PROTOCOL);
        transport.connect(config.getMAIL_HOST(), config.getMAIL_USERNAME(), config.getMAIL_PASSWORD());
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}
