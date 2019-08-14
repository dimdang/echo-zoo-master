package org.cool.zoo.api;

import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.pwd.ResetPwd;
import org.cool.zoo.service.DealerService;
import org.cool.zoo.service.StaffService;
import org.cool.zoo.util.JResponseEntity;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = Routes.MOBILE)
public class ResetPwdController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private DealerService dealerService;

    @PostMapping(value = "rest-password", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JResponseEntity resetPassword(ResetPwd resetPwd) {
        List<ResetPwd> resetPwds = new ArrayList<>();
        try {
            if (dealerService.isReallyEmailInuse(resetPwd.getEmail())) {
                if (dealerService.resetPassword(resetPwd)) {
                    resetPwds.add(resetPwd);
                }
            } else if (staffService.findByEmail(resetPwd.getEmail()) != null) {
                if (staffService.resetPassword(resetPwd)) {
                    resetPwds.add(resetPwd);
                }
            } else {
                return ResponseFactory.build("No User Found", HttpStatus.NOT_FOUND.value(), resetPwds);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseFactory.fail();
        }
        return ResponseFactory.build("Reset password successful", HttpStatus.OK.value(), resetPwds);
    }
}
