
import java.io.IOException;
import java.util.*;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;


public class SendEmail {

    private String smtpHostname ;
    private String user ;
    private String passwd;
    private String data;
    private String response;
    private String subject;
    private String mailFrom;
    private String mailTo;
    
    public SendEmail(String smtpHostname, String user, String passwd, String data, String subject, String mailFrom,
            String mailTo) {
        super();
        this.smtpHostname = smtpHostname;
        this.user = user;
        this.passwd = passwd;
        this.data = data;
        this.subject = subject;
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
    }


    
    public String getSmtpHostname() {
        return smtpHostname;
    }


    public void setSmtpHostname(String smtpHostname) {
        this.smtpHostname = smtpHostname;
    }


    public String getUser() {
        return user;
    }


    public void setUser(String user) {
        this.user = user;
    }


    public String getPasswd() {
        return passwd;
    }


    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }


    public String getData() {
        return data;
    }


    public void setData(String data) {
        this.data = data;
    }


    public String getResponse() {
        return response;
    }


    public void setResponse(String response) {
        this.response = response;
    }


    public boolean send() {

        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", smtpHostname);

        // Get the default Session object.
        // Session session = Session.getDefaultInstance(properties);

        // create a session with an Authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, passwd);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(mailFrom));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    mailTo));

            // Set Subject: header field
            message.setSubject(subject);
            message.setDataHandler(new DataHandler(
                    new ByteArrayDataSource(data, "text/html")));
            
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException  mex) {
            mex.printStackTrace();
        } catch (IOException iox){
            iox.printStackTrace();
        }
        return true;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getMailFrom() {
        return mailFrom;
    }


    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }


    public String getMailTo() {
        return mailTo;
    }


    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }
    
}
