package com.example.memo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;



public void sendEmail(String to, String subject, String name, String verificationUrl) {
    try {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String htmlContent = """
        <html>
        <body style="font-family:Arial,sans-serif; background-color:#f9f9f9; padding:20px;">
            <div style="max-width:600px;margin:auto;background:white;padding:20px;border-radius:8px;box-shadow:0 2px 8px rgba(0,0,0,0.1);">
                <h2 style="color:#333;">Hello %s,</h2>
                <p style="color:#555;">Thanks for registering. Please click the button below to verify your account:</p>
                <a href="%s" style="display:inline-block;margin-top:20px;padding:10px 20px;background-color:#4CAF50;color:white;text-decoration:none;border-radius:4px;">Verify Account</a>
                <p style="color:#999;margin-top:30px;font-size:12px;">If you did not request this, please ignore this email.</p>
            </div>
        </body>
        </html>
        """.formatted(name, verificationUrl);

        helper.setFrom("ams.project.105@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);  // true for HTML

        javaMailSender.send(message);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
