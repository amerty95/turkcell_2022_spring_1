package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProJoinCatRepository;
import com.works.repositories.ProductRepository;
import com.works.utils.ERest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ProductService {

    final ProductRepository pRepo;
    final ProJoinCatRepository joinCatRepository;
    public ProductService(ProductRepository pRepo, ProJoinCatRepository joinCatRepository) {
        this.pRepo = pRepo;
        this.joinCatRepository = joinCatRepository;
    }

    public ResponseEntity save(Product product) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        hm.put(ERest.result, pRepo.save(product));
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity list( int cid ) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        hm.put(ERest.result, joinCatRepository.joinList(cid) );
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
