package org.cool.zoo.api;

import org.cool.zoo.entities.pwd.MailParam;
import org.cool.zoo.repositories.StaffRepo;
import org.cool.zoo.service.DealerService;
import org.cool.zoo.service.EmailService;
import org.cool.zoo.util.JResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

//@RestController
public class EmailController {

    @Autowired
    private DealerService dealerService;

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private EmailService emailService;

    List<MailParam> mailParams = null;

    @PostMapping(value = "email/reset-password")
    public ResponseEntity resetPassword(@RequestBody MailParam param) throws MessagingException {
        JResponseEntity body = new JResponseEntity();
        if (param != null) {
            emailService.sendSimpleMessage(param.getEmail());
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Mail send successfully");
            mailParams = new ArrayList<>();
            mailParams.add(param);

            body.setData(mailParams);
        }
        return ResponseEntity.ok(body);
    }

}
