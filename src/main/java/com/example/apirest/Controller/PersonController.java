package com.example.apirest.Controller;

import com.example.apirest.Entity.Person;
import com.example.apirest.Repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping(value = "/person")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping(value = "/person/{id}")
    public Optional<Person> GetPersonById(@PathVariable Long id){
        return personRepository.findById(id);
    }
}
