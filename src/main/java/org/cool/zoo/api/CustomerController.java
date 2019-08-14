package org.cool.zoo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cool.zoo.configure.Routes;
import org.cool.zoo.entities.customer.CusParam;
import org.cool.zoo.service.CustomerService;
import org.cool.zoo.util.JResponseEntity;
import org.cool.zoo.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = Routes.MOBILE)
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    List<CusParam> cusParams;

    @PostMapping(value = "customer/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public JResponseEntity createNewCustomerForm(@RequestPart(required = false) MultipartFile[] files, @RequestPart String json) throws IOException {
        try {

            CusParam param = objectMapper.readValue(json, CusParam.class);
            if (files != null && files.length > 0 && param != null) {
                param = customerService.saveNewCustomer(files, param);
                if (param != null) {
                    cusParams = new ArrayList<>();
                    cusParams.add(param);
                }else return ResponseFactory.build("Submit failed some thing went wrong", HttpStatus.BAD_REQUEST.value(), cusParams);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseFactory.fail();
        }

        return ResponseFactory.build("Submit success", HttpStatus.CREATED.value(), cusParams);
    }

    @PutMapping(value = "customer/submit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public JResponseEntity updateCustomerForm(@RequestPart(required = false) MultipartFile[] files, @RequestPart String json) throws IOException {
        try {

            CusParam param = objectMapper.readValue(json, CusParam.class);
            if (files != null && files.length > 0 && param != null) {
                param = customerService.saveNewCustomer(files, param);
                if (param != null) {
                    cusParams = new ArrayList<>();
                    cusParams.add(param);
                }else return ResponseFactory.build("Update fail some thing went wrong", HttpStatus.BAD_REQUEST.value(), cusParams);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseFactory.fail();
        }
        return ResponseFactory.build("Update success", HttpStatus.CREATED.value(), cusParams);
    }
}
