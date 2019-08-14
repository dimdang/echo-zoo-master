package org.cool.zoo.service;

import org.cool.zoo.entities.users.Staff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by DANG DIM
 * Date     : 2/28/2018, 9:51 AM
 * Email    : d.dim@gl-f.com
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecStaffServiceTest {

    @Autowired
    private StaffService userService;

    Staff ss = new Staff();


    @Test
    public void findByUsername() {
        ss.setUsername("admin");
        userService.findByUsername(ss.getUsername());
    }
}