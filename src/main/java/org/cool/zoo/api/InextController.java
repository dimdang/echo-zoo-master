package org.cool.zoo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InextController {

    @GetMapping(value = "/")
    public String inext(){
        return "Welcome to heroku";
    }
}
