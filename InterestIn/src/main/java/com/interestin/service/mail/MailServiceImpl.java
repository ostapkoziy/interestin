package com.interestin.service.mail;

import com.interestin.service.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by dbatuik on 29.01.14.
 */
@Service
public class MailServiceImpl implements MailService {
    private static final Logger log = Logger.getLogger(MailServiceImpl.class);

    @Autowired
    private MessageSource messageSource;

    @Async
    @Override
    public void sendUserActivationMail(String mailTo, String activationUrl, Locale locale) {
        try {
            log.info("Send activation email to " + mailTo + " using locale " + locale);
            Message message = new MimeMessage(getMailSession());
            message.setFrom(new InternetAddress("noreplyinterestin@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailTo));
            String[] args = {activationUrl};
            message.setSubject(messageSource.getMessage("email.activation.subject", null, locale));
            message.setText(messageSource.getMessage("email.activation.body", args, locale));

            Transport.send(message);
            log.info("Send!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Session getMailSession() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("noreplyinterestin", "interestinsmtp");
                    }
                });
        return session;
    }
}

