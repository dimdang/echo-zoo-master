package org.cool.zoo.api;

import org.cool.zoo.configure.Routes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Routes.FREE)
public class IndexController {

    @GetMapping(value = "/test")
    public String index(){
        return "Welcome to heroku";
    }
}
