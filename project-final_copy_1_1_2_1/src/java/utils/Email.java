package utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    public static boolean sendEmailTo(String to, String OTP){
        String from = "thienltnse182117@fpt.edu.vn";
        String password = "sizkmmwxljqxxhcg";
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password); //To change body of generated methods, choose Tools | Templates.
            }
        };
                Session session = Session.getInstance(props, auth);
        
        MimeMessage msg = new MimeMessage(session);
        
                
        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(from);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject("Xác nhận đổi mật khẩu");
            msg.setSentDate(new Date());
            //msg.setReplyTo(InternetAddress.parse(from, false));
            
            msg.setContent("Mã OTP của bạn là: "+OTP, "text/HTML; charset=UTF-8");
            
            Transport.send(msg);
            
            
            System.out.println("Đã gửi OTP đến "+to);
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi gửi mail");
            e.printStackTrace();
        }

        return false;
    }
    
    public static void main(String[] args) {
        System.out.println(Email.sendEmailTo("holong51396@gmail.com", "614812"));
    }

}
