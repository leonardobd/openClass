package com.OpenClassRest.OpenClass.Service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import com.OpenClassRest.OpenClass.Interface.Service.IMailService;

import org.springframework.stereotype.Service;

@Service("mailService")
@Transactional
public class MailService implements IMailService {

    @Override
    public void enviar(String destinatario, String asunto, String cuerpo) throws Exception {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("openclassproyect@gmail.com", "yf1EUfRRnR$etMAFGnOH1.");
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("openclassproyect@gmail.com"));
            message.setSubject(asunto);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(destinatario));
            message.setContent(cuerpo,"text/html; charset=utf-8");
            // Envia el mensaje
            Transport.send(message);

        } catch (Exception e) {
            System.out.println("error:{msg:'error.MailService.enviar:" + e.getMessage() + "'}");
            throw e;
        }
    }
    
}
