package kz.aibat.springboot.security.security.controller;

import kz.aibat.springboot.security.security.model.UserAuth;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

    protected UserAuth getCurrentUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            UserAuth currentUser = (UserAuth) authentication.getPrincipal();
            return currentUser;
        }
        return null;
    }

}
