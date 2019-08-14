package org.cool.zoo.service;

import org.cool.zoo.encode.DefaultPwd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

public class MailService {

    @Autowired
    public JavaMailSender javaMailSender;

    public void sendDefaultEmailPwd(String email) {
        try {
            if (!email.isEmpty()) {
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo(email);
                msg.setSentDate(new Date());
                msg.setSubject("Default Password");
                msg.setText("Your default password is : " + DefaultPwd.defaultPassword);
                javaMailSender.send(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
