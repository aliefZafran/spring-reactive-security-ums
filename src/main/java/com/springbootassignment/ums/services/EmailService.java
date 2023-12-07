package com.springbootassignment.ums.services;

import com.springbootassignment.ums.models.MyUser;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }


    private void sendEmail(MyUser user, String subject, String content)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "noreply@yourdomain.com";
        String senderName = "Your Company";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    public void sendVerificationEmail(MyUser user)
            throws MessagingException, UnsupportedEncodingException {
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        content = content.replace("[[name]]", user.getFirstName() + " " + user.getLastName());
        String verifyURL = "http://localhost:8000/api/verify?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);

        sendEmail(user, subject, content);
    }

    public void sendResetPasswordEmail(MyUser user)
            throws MessagingException, UnsupportedEncodingException {
        String subject = "Reset Your Password";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to reset your password:<br>"
                + "<h3><a href=\"[[URL]]\">RESET PASSWORD</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        content = content.replace("[[name]]", user.getFirstName() + " " + user.getLastName());
        String resetURL = "http://localhost:8000/api/reset-password?token=" + user.getResetToken();
        content = content.replace("[[URL]]", resetURL);

        sendEmail(user, subject, content);
    }

}
