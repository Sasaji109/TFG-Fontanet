package com.example.tfgfontanet.common.utiles.mail;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.common.configuracion.ConfiguracionMail;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Log4j2
@Singleton
public class MandarMail {

    private final ConfiguracionMail config;

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

    public void generateAndSendEmailWithAttachment(String to, String msg, String subject, File attachment) throws MessagingException, IOException {
        Properties mailServerProperties = new Properties();
        mailServerProperties.put(Constantes.MAIL_SMTP_PORT, config.getMAIL_SMTP_PORT());
        mailServerProperties.put(Constantes.MAIL_SMTP_AUTH, config.getMAIL_SMTP_AUTH());
        mailServerProperties.put(Constantes.MAIL_SMTP_SSL_TRUST, config.getMAIL_SMTP_SSL_TRUST());
        mailServerProperties.put(Constantes.MAIL_SMTP_STARTTLS_ENABLE, config.getMAIL_SMTP_STARTTLS_ENABLE());

        Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        MimeMessage generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        generateMailMessage.setSubject(subject);

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(msg);

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(attachment);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachmentBodyPart);

        generateMailMessage.setContent(multipart);

        Transport transport = getMailSession.getTransport(Constantes.PROTOCOL);
        transport.connect(config.getMAIL_HOST(), config.getMAIL_USERNAME(), config.getMAIL_PASSWORD());
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}
