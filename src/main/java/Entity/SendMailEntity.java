
package Entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public class SendMailEntity {
    
    @NotBlank(message = "To Email is Required")
    @Email(message = "Enter the Valid Email ")
    private String to;
    @NotBlank(message = "Subject is Required")
    private String subject;
    @NotBlank(message = "Message is Required")
    private String message;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SendMailModel{" + "to=" + to + ", subject=" + subject + ", message=" + message + '}';
    }
    
    


    
}

