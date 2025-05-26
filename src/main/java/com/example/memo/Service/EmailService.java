package com.example.memo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String username, String link) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("ams.project.105@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        String htmlContent = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<style>" +
                "  .email-container { font-family: Arial, sans-serif; padding: 20px; background-color: #f9f9f9; }" +
                "  .email-content { max-width: 600px; margin: auto; background: #fff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }" +
                "  .button { background-color: #4CAF50; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='email-container'>" +
                "  <div class='email-content'>" +
                "    <h2>Hello, " + username + "!</h2>" +
                "    <p>Thank you for registering. Please click the button below to verify your account:</p>" +
                "    <p><a class='button' href='" + link + "'>Verify Account</a></p>" +
                "    <p>If you didn't request this, you can safely ignore this email.</p>" +
                "    <p>Best regards,<br>The Memo Team</p>" +
                "  </div>" +
                "</div>" +
                "</body>" +
                "</html>";

        helper.setText(htmlContent, true); // true = isHtml
        javaMailSender.send(message);
    }
}
