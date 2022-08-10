package com.works.restcontrollers;

import com.works.entities.Admin;
import com.works.services.AdminDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminRestController {


    final AdminDetailService service;
    public AdminRestController(AdminDetailService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Admin admin) {
        return service.register(admin);
    }

}
