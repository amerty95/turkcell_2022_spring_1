package com.works.controllers;

import com.works.entitites.Admin;
import com.works.services.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    final AdminService aService;
    public LoginController(AdminService aService) {
        this.aService = aService;
    }


    String error = "";
    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("error", error);
        error = "";
        return "login";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(Admin admin) {
        boolean status = aService.login(admin);
        if ( status ){
            return "redirect:/dashboard";
        }
        error = "Email or Password Fail";
        return "redirect:/";
    }

}
