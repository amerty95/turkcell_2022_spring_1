package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    final ProductService pService;
    public ProductRestController(ProductService pService) {
        this.pService = pService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Product product) {
        return pService.save(product);
    }

    @GetMapping("/list/{cid}")
    public ResponseEntity list( @PathVariable int cid ) {
        return pService.list(cid);
    }

}
