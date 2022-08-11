package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.services.CustomerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    final CustomerService cService;
    public CustomerRestController(CustomerService cService) {
        this.cService = cService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Customer customer) {
        return cService.save(customer);
    }

    @PostMapping("/saveAll")
    public ResponseEntity saveAll(@RequestBody List<Customer> customers) {
        return cService.saveAll( customers );
    }

    @Cacheable("customerList")
    @GetMapping("/list")
    public ResponseEntity list() {
        return cService.list();
    }

    @GetMapping("/page/{number}")
    public ResponseEntity page( @PathVariable int number ) {
        return cService.listPagable(number);
    }

    @GetMapping("/delete/{stCid}")
    public ResponseEntity delete( @PathVariable String stCid ) {
        return cService.delete(stCid);
    }

    @GetMapping("/search")
    public ResponseEntity search( @RequestParam String q, @RequestParam(defaultValue = "0") int page ) {
        return cService.search(q, page);
    }

    @PostMapping("/login")
    public ResponseEntity login( @RequestBody Customer customer ) {
        return cService.login(customer.getEmail(), customer.getPassword());
    }

}
