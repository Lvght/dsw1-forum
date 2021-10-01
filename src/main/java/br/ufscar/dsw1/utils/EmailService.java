package br.ufscar.dsw1.utils;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailService {

    public void send(InternetAddress from, InternetAddress to, String subject, String body, File file) {

        try {
            Properties properties = new Properties();

            final String username = System.getenv("GMAIL_ADDRESS");
            final String password = System.getenv("GMAIL_APP_PASSWORD");
            final String host = System.getenv("EMAIL_PROVIDER_HOST");
            final String port = System.getenv("EMAIL_PROVIDER_PORT");

            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.starttls.required", "true");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(from);
            message.setRecipient(Message.RecipientType.TO, to);
            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(body, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            if (file != null) {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.attachFile(file);
                multipart.addBodyPart(attachmentBodyPart);
            }

            message.setContent(multipart);
            Transport.send(message);

            System.out.println("Mensagem enviada com sucesso!");

        } catch (Exception e) {
            System.out.println("Mensagem não enviada!");
            e.printStackTrace();
        }
    }

    public void send(InternetAddress from, InternetAddress to, String subject, String body) {
        send(from, to, subject, body, null);
    }
}
