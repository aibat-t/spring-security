package kz.aibat.springboot.security.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController extends BaseController {

    @GetMapping(value = "/")
    public String indexPage(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "index";
    }

    @GetMapping(value = "/signin")
    public String signInPage(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "signin";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "profile";
    }

    @GetMapping(value = "/adminpanel")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String adminPanel(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "adminpanel";
    }

    @GetMapping(value = "/moderatorpanel")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    public String moderatorPanel(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "moderatorpanel";
    }

    @GetMapping(value = "/403")
    public String accessDeniedPage(Model model){
        model.addAttribute("currentUser", getCurrentUser());
        return "403";
    }
}
