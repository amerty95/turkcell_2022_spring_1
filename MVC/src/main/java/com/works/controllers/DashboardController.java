package com.works.controllers;

import com.works.entitites.Person;
import com.works.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class DashboardController {

    final PersonService pService;
    public DashboardController(PersonService pService) {
        this.pService = pService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("list", pService.list());
        model.addAttribute("rest", pService.restResult());
        return "dashboard";
    }


    @PostMapping("/personAdd")
    public String personAdd(Person person) {
        pService.save(person);
        return "redirect:/dashboard";
    }

    @GetMapping("/personDelete/{pid}")
    public String personDelete(@PathVariable int pid) {
        pService.delete(pid);
        return "redirect:/dashboard";
    }


}
