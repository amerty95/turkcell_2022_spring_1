package com.works.services;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import com.works.utils.ERest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminDetailService implements UserDetailsService {

    final AdminRepository aRepo;
    final PasswordEncoder encoder;
    public AdminDetailService(AdminRepository aRepo, PasswordEncoder encoder) {
        this.aRepo = aRepo;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public ResponseEntity register(Admin admin) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        Optional<Admin> optionalAdmin = aRepo.findByUsernameEqualsIgnoreCase( admin.getUsername() );
        if (optionalAdmin.isPresent() ) {
            // bu kullanıcı daha önce kayıtlı
            hm.put(ERest.status, false);
            hm.put(ERest.message, "Bu kullanıcı daha önce kayıtlı");
            hm.put(ERest.result, admin);
        }else {
            admin.setPassword( encoder.encode( admin.getPassword() ) );
            aRepo.save( admin );
            hm.put(ERest.status, true);
            hm.put(ERest.result, admin);
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
