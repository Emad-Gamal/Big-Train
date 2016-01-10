/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author bilal
 */
public class MailClient {
        private class SMTPAuthenticator extends Authenticator {

        private PasswordAuthentication authentication;

        public SMTPAuthenticator(String login, String password) {
            authentication = new PasswordAuthentication(login, password);
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }

    public void mail(String to) {
        
            String from = "trainReservationSystem@gmail.com";
            //String to = "jakloove@gmail.com";
            String subject = "Reservation Confirmation Mail";
            String message = "Your Reservation for the train Trip Has Been Accepted";
            String login = "trainReservationSystem@gmail.com";
            String password = "123456789AA";

            Properties props = new Properties();
            props.setProperty("mail.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.starttls.enable", "true");

            Authenticator auth = new SMTPAuthenticator(login, password);

            Session session = Session.getInstance(props, auth);

            MimeMessage msg = new MimeMessage(session);

            try {
                msg.setText(message);
                msg.setSubject(subject);
                msg.setFrom(new InternetAddress(from));
                msg.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(to));
                Transport.send(msg);
            } catch (MessagingException ex) {
                Logger.getLogger(MailClient.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        
    }
    
    
    public void cancellatiomMail(String to) {
        
            String from = "trainReservationSystem@gmail.com";
            //String to = "jakloove@gmail.com";
            String subject = "Reservation Cancellation Mail";
            String message = "Your Reservation for the train Trip Has Been Cancelled Upon your Request, If You think that this happend by mistake please call us immediatly on 199991 ";
            String login = "trainReservationSystem@gmail.com";
            String password = "123456789AA";

            Properties props = new Properties();
            props.setProperty("mail.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.starttls.enable", "true");

            Authenticator auth = new SMTPAuthenticator(login, password);

            Session session = Session.getInstance(props, auth);

            MimeMessage msg = new MimeMessage(session);

            try {
                msg.setText(message);
                msg.setSubject(subject);
                msg.setFrom(new InternetAddress(from));
                msg.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(to));
                Transport.send(msg);
            } catch (MessagingException ex) {
                Logger.getLogger(MailClient.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        
    }
}
