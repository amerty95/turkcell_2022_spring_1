package com.works.services;

import com.works.entitites.Person;
import com.works.props.JsonUser;
import com.works.repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PersonService {

    final PersonRepository pRepo;
    public PersonService(PersonRepository pRepo) {
        this.pRepo = pRepo;
    }

    public boolean save(Person person) {
        try {
            pRepo.save(person);
            return true;
        }catch (Exception ex) {
            return false;
        }
    }

    public List<Person> list() {
        return pRepo.findAll();
    }

    public boolean delete( int pid ) {
        try {
            pRepo.deleteById(pid);
            return true;
        }catch (Exception ex) {
            return false;
        }
    }

    public List<JsonUser> restResult() {
        String url = "https://jsonplaceholder.typicode.com/users";
        RestTemplate template = new RestTemplate();
        List<JsonUser> jsonUsers = template.getForObject(url, List.class);
        return jsonUsers;
    }


}
