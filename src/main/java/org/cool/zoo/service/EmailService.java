package org.cool.zoo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String mail) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setText("Your default password", true);
        helper.setSubject("Reset pwd");
        helper.setTo(mail);
        helper.setFrom("dangdim@aeon.com.kh");

        emailSender.send(message);
    }

}
