package twoFactor;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Stateless
@Asynchronous
public class EmailClient {
    private static final String senderEmail = "5f8bbb26816e39";//change with your sender email
    private static final String senderPassword = "73b115b2e19608";//change with your sender password //TODO: mail trap account

    public void sendAsHtml(String to, String title, String html) throws MessagingException {

        Session session = createSession();

        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message, to, title, html);

        //sending message
        Transport.send(message);

    }

    //Setup for email contents
    private void prepareEmailMessage(MimeMessage message, String to, String title, String html) throws MessagingException {
        message.setContent(html, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress("Da@One.com.ua.ru"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
    }

    // Mail-server with properties
    private Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");//Outgoing server requires authentication
        props.put("mail.smtp.starttls.enable", "true");//TLS must be activated
        props.put("mail.smtp.host", "smtp.mailtrap.io"); //Outgoing server (SMTP) - change it to your SMTP server
        props.put("mail.smtp.port", "2525");//Outgoing port

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        return session;
    }

}
