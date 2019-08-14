package org.cool.zoo.api;

import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.param.DealerParam;
import org.cool.zoo.entities.users.Dealer;
import org.cool.zoo.repositories.DealerRepo;
import org.cool.zoo.service.AuditService;
import org.cool.zoo.service.DealerService;
import org.cool.zoo.util.JResponseEntity;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Routes.MOBILE)
public class DealerController {

    @Autowired
    private DealerRepo dealerRepo;

    @Autowired
    private DealerService dealerService;

    private List<Dealer> dealers = null;

    @Autowired
    private AuditService auditService;

    @GetMapping(value = "/get-dealer-by-code")
    public JResponseEntity getDealer(@RequestParam String dealerCode) {

        try {
            if (!dealerCode.isEmpty()) {
                dealers = dealerRepo.getByDealerCode(dealerCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseFactory.fail();
        }
        return ResponseFactory.build(dealers);
    }


    @PostMapping(value = "/register-dealer", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JResponseEntity registerDealer(@RequestBody DealerParam dealer){
        try {
            if (dealer != null){
                dealers = new ArrayList<>();

                Dealer resDealer  = dealerService.newDealer(dealer);
                if (resDealer != null){
                    dealers.add(resDealer);
                    auditService.saveLoging(resDealer.getDealerCode(), "Register Dealer", "Staff/Register Dealer");
                }else {
                    ResponseFactory.build("Register failed", HttpStatus.GONE.value(), dealers);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseFactory.fail();
        }

        return ResponseFactory.created(dealers);
    }

}
