
package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import Entity.SendMailEntity;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Value("${spring.mail.username}")
    String username;
 
    @Autowired
    private JavaMailSender javaMailSender;
    
    public void sendMail(SendMailEntity em)
    {
       try{
            
             MimeMessage message= javaMailSender.createMimeMessage();
             MimeMessageHelper helper=new MimeMessageHelper(message);
             helper.setFrom(username);
             helper.setTo(em.getTo());
             helper.setSubject(em.getSubject());
             helper.setText(em.getMessage());
             javaMailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    public void SendRegisterUserMail(String toMail,String UserSubject,String UserMessage)
    {
       try{
            
             MimeMessage message= javaMailSender.createMimeMessage();
             MimeMessageHelper helper=new MimeMessageHelper(message);
             helper.setFrom(username);
             helper.setTo(toMail);
             helper.setSubject(UserSubject);
             helper.setText(UserMessage);
             javaMailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }     
    }
    
    
    
    
    
}
