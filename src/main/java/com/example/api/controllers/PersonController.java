package com.example.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.api.models.*;
import com.example.api.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public ResponseEntity<List<PersonModel>> getAllPersons() {
        return ResponseEntity.ok().body(personService.getAllPersons());
    }
    @GetMapping("/person/{id}")
    public ResponseEntity<Optional<PersonModel>> getPersonById(@PathVariable(value = "id") String personId){
        return ResponseEntity.ok().body(personService.getPersonById(personId));
    }
    @PostMapping("/post_person")
    public void createPerson(@RequestBody PersonModel personModel) {
        personService.createPerson(personModel);
    }
    @PutMapping("/put_person/{id}")
    public void putPersonById(@PathVariable(value = "id") String personId, @RequestBody PersonModel personModel){
        personService.putPersonById(personId,personModel);
    }
    @DeleteMapping("/delete_person/{id}")
    public void deletePerson(@PathVariable(value = "id") String personId) {
        personService.deletePerson(personId);
    }
    @GetMapping("/verify/{name}/{pass}")
    public ResponseEntity<Boolean> getVerify(@PathVariable(value = "name") String personName, @PathVariable(value = "pass") String personPass) {
        return ResponseEntity.ok().body(personService.getVerify(personName, personPass));
    }

}
