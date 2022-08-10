package com.works.services;

import com.works.entities.Customer;
import com.works.repositories.CustomerRepository;
import com.works.utils.ERest;
import com.works.utils.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    final CustomerRepository cRepo;
    final Random rnd1;
    public CustomerService(CustomerRepository cRepo, Random rnd1) {
        this.cRepo = cRepo;
        this.rnd1 = rnd1;
    }

    public ResponseEntity saveAll(List<Customer> customers) {
        Map<ERest, Object> hm =  new LinkedHashMap<>();
        List<ResponseEntity> ls = new ArrayList<>();
        for ( Customer item : customers ) {
            //item.setPassword( Util.MD5(item.getPassword()) );
            ls.add(save(item));
        }
        return new ResponseEntity(ls, HttpStatus.OK);
    }

    public ResponseEntity save(Customer customer) {
        Map<ERest, Object> hm =  new LinkedHashMap<>();
        try {
            customer.setPassword( Util.MD5( customer.getPassword()) );
            cRepo.save(customer);
            hm.put(ERest.status, true);
            hm.put(ERest.result, customer);
            return new ResponseEntity( hm, HttpStatus.OK );
        }catch (Exception ex) {
            String message = ex.getMessage();
            if ( message.contains("ConstraintViolationException") ) {
                message = "Bu mail adresi daha önce kayıtlı!";
            }
            hm.put(ERest.status, false);
            hm.put(ERest.message, message);
            hm.put(ERest.result, customer);
            return new ResponseEntity( hm, HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }


    public ResponseEntity list() {
        Map<ERest, Object> hm =  new LinkedHashMap<>();
        hm.put(ERest.status, true);
        hm.put(ERest.result, cRepo.findAll());
        return new ResponseEntity( hm, HttpStatus.OK );
    }

    public ResponseEntity listPagable( int page ) {
        Map<ERest, Object> hm =  new LinkedHashMap<>();
        hm.put(ERest.status, true);
        Pageable pageable = PageRequest.of(page, 10);
        hm.put(ERest.result, cRepo.findAll(pageable) );
        return new ResponseEntity( hm, HttpStatus.OK );
    }


    public ResponseEntity delete( String stCid ) {
        Map<ERest, Object> hm =  new LinkedHashMap<>();
        try {
            int cid = Integer.parseInt( stCid );
            Optional<Customer> optionalCustomer = cRepo.findById( cid );
            if (optionalCustomer.isPresent() ) {
                // bu veri veritabanında var!, Silme işlemini yap
                Customer deleteCustomer = optionalCustomer.get();
                cRepo.deleteById(cid);
                hm.put(ERest.status, true);
                hm.put(ERest.result, deleteCustomer);
                return new ResponseEntity( hm, HttpStatus.OK );

            }else {
                //  veritabanında böyle bir data yok!
                hm.put(ERest.status, false);
                hm.put(ERest.result, stCid);
                return new ResponseEntity( hm, HttpStatus.BAD_REQUEST );
            }

        }catch (Exception ex) {
            hm.put(ERest.status, false);
            hm.put(ERest.result, ex.getMessage());
            return new ResponseEntity( hm, HttpStatus.INTERNAL_SERVER_ERROR );
        }

    }

    public ResponseEntity search( String q, int page ) {
        Map<ERest, Object> hm =  new LinkedHashMap<>();
        Pageable pageable = PageRequest.of(page, 10);
        Page<Customer> ls = cRepo.findByNameContainsIgnoreCase(q, pageable);

        if ( ls.getNumberOfElements() > 0 ) {
            hm.put(ERest.status, true);
            hm.put(ERest.result, ls);
        }else {
            hm.put(ERest.status, false);
        }

        return new ResponseEntity( hm, HttpStatus.OK );
    }


    public ResponseEntity login( String email, String password ) {
        Map<ERest, Object> hm =  new LinkedHashMap<>();
        password = Util.MD5( password );
        email = email.trim();
        Optional<Customer> optionalCustomer = cRepo.findByEmailEqualsIgnoreCaseAndPasswordEquals(email, password);
        if ( optionalCustomer.isPresent() ) {
            Customer customer = optionalCustomer.get();
            hm.put(ERest.status, true);
            hm.put(ERest.result, customer);
        }else {
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Email or Password Fail");
        }
        return new ResponseEntity( hm, HttpStatus.OK );
    }


}
