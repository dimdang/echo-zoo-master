package org.cool.zoo.api;

import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.Branch;
import org.cool.zoo.entities.users.Staff;
import org.cool.zoo.repositories.BranchRepo;
import org.cool.zoo.repositories.StaffRepo;
import org.cool.zoo.util.JResponseEntity;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = Routes.MOBILE)
public class BranchController {

    @Autowired
    private BranchRepo branchRepo;

    @Autowired
    private StaffRepo staffRepo;

    List<Branch> branches = null;

    @GetMapping(value = "/get-all-branches", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JResponseEntity getAllBranches() {

        try {
            branches = branchRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseFactory.fail();
        }
        return ResponseFactory.build(branches);
    }

    @GetMapping(value = "/fetch-branches-by-staff", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JResponseEntity fetchAllBranchOfStaff(@RequestParam("loginId") String loginId) {
        Staff staff = null;
        try {
            if (loginId != null && !loginId.isEmpty()){
                staff =staffRepo.findByLoginId(loginId);
                if (staff != null && staff.getBranch() != null){
                    branches = Arrays.asList(staff.getBranch());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseFactory.fail();
        }
        return ResponseFactory.build(branches);
    }
}
