package com.example.memo.Service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;



@Service
public class EmailService {


    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text) {

        SimpleMailMessage message=new SimpleMailMessage();

        message.setFrom("ams.project.105@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
   
}
}
