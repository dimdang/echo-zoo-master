package org.cool.zoo.api;

import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.users.Dealer;
import org.cool.zoo.entities.users.Staff;
import org.cool.zoo.entities.users.User;
import org.cool.zoo.repositories.DealerRepo;
import org.cool.zoo.repositories.StaffRepo;
import org.cool.zoo.util.JResponseEntity;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = Routes.MOBILE)
public class UserController {

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private DealerRepo dealerRepo;

    List<User> users = null;


    @GetMapping(value = "/get-login-user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    JResponseEntity getLoginUser(@RequestParam("loginId") String loginId){

        try {
            if (loginId != null && !loginId.isEmpty()) {

                Dealer dealer = dealerRepo.findByDealerCode(loginId);
                Staff staff = staffRepo.findByLoginId(loginId);

                users = new ArrayList<>();
                User user = new User();

                if (dealer != null){
                    user.setId(dealer.getDealerId());
                    user.setLoginName(dealer.getDealerName());
                    user.setLoginId(dealer.getDealerCode());
                    user.setLoginType("DEALER");
                }else if (staff != null){
                    user.setId(staff.getId());
                    user.setLoginId(staff.getLoginId());
                    user.setLoginName(staff.getUsername());
                    user.setLoginType("STAFF");
                }

                users.add(user);

            }
        } catch (Exception e) {
            return ResponseFactory.build(users);
        }
        return ResponseFactory.build(users);
    }
}
