package kz.aibat.springboot.security.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String indexPage(){
        return "index";
    }

    @GetMapping(value = "/signin")
    public String signInPage(){
        return "signin";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(){
        return "profile";
    }
    //asvasvasv
}
