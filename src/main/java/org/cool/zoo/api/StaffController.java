package org.cool.zoo.api;

import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.users.Dealer;
import org.cool.zoo.entities.users.Staff;
import org.cool.zoo.repositories.StaffRepo;
import org.cool.zoo.util.JResponseEntity;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Routes.MOBILE)
public class StaffController {

    @Autowired
    private StaffRepo staffRepo;
    private List<Dealer> dealers = null;
    private Staff staff;

    @GetMapping(value = "fetch-dealer-by-staff")
    JResponseEntity getAllDealerForStaff(@RequestParam("loginId") String loginId){
        try {
            if (loginId != null && !loginId.isEmpty()){
                staff = staffRepo.findByLoginId(loginId);

                if (staff != null){
                    dealers = staff.getDealers();
                    dealers.forEach(dealer -> {
                        if (dealer.getProducts() != null && !dealer.getProducts().isEmpty()){
                            dealer.getProducts().forEach(product -> {
                                if (product.isDeleted())
                                    dealers.remove(product);
                            });
                        }
                    });
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            ResponseFactory.fail();
        }
        return ResponseFactory.build(dealers);
    }
}
