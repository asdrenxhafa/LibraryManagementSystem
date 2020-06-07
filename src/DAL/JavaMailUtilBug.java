/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Rezervimet;
import java.util.Properties;
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
 * @author Asdren
 */
public class JavaMailUtilBug {
    
    public static void sendEmail(String recepient,String titulli,String permbajtja) throws MessagingException{
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccount = "projektilibrary@gmail.com";
        String password = "projektilibrary123";
        
        
        Session session = Session.getInstance(properties, new Authenticator(){
           @Override
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(myAccount,password);
           }
        });
        
        Message message = prepareMessage(session,myAccount,recepient,titulli,permbajtja);
        
        Transport.send(message);
    }

    private static Message prepareMessage(Session session,String myAccount,String recepient,String titulli,String permbajtja) {
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Bug ne Aplikacion");
            message.setText(titulli+"\n"+permbajtja);
            return message;
        }catch(Exception e){
        }
        return null;
    }
}
