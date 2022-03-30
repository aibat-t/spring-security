package kz.aibat.springboot.security.security.controller;

import kz.aibat.springboot.security.security.model.UserAuth;
import kz.aibat.springboot.security.security.repository.AuthUserRepository;
import kz.aibat.springboot.security.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController extends BaseController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

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

    @GetMapping(value = "/signup")
    public String signUp(Model model){
        model.addAttribute("currentUser", getCurrentUser());

        return "signup";
    }

    @PostMapping(value = "tosignup")
    public String toSignUp(@RequestParam(name = "user_email") String email,
                           @RequestParam(name = "user_full_name") String fullName,
                           @RequestParam(name = "user_password") String password,
                           @RequestParam(name = "user_re_password") String rePassword){

        if(password.equals(rePassword)){

            UserAuth checkUser = userService.getUserByEmail(email);
            if(checkUser == null){
                UserAuth user = new UserAuth();
                user.setEmail(email);
                user.setFullName(fullName);
                user.setPassword(passwordEncoder.encode(password));

                userService.createUser(user);

                return "redirect:/signup?success";
            }
            return "redirect:/signup?emailerror";
        }
        return "redirect:/signup?passworderror";
    }

    @PostMapping(value = "updatepassword")
    @PreAuthorize("isAuthenticated()")
    public String toUpdatePAssword(@RequestParam(name = "user_old_password") String oldPassword,
                           @RequestParam(name = "user_new_password") String newPassword,
                           @RequestParam(name = "user_new_re_password") String reNewPassword){

        if(newPassword.equals(reNewPassword)){

            UserAuth currentUser = getCurrentUser();
            UserAuth checkUser = userService.getUserByEmail(currentUser.getEmail());

            if(passwordEncoder.matches(oldPassword, checkUser.getPassword())){

                currentUser.setPassword(passwordEncoder.encode(newPassword));
                userService.updateUser(currentUser);
                return "redirect:/profile?success";
            }
            return "redirect:/profile?oldpassworderror";
        }
        return "redirect:/profile?passworderror";
    }
}
