package com.example.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.api.models.*;
import com.example.api.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PersonController {
    Logger logger = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    private PersonService personService;

    @GetMapping("/person/{pageNo}/{pageSize}")
    public List<PersonModel> getAllPersons(@PathVariable int pageNo, @PathVariable int pageSize) {
        logger.info("Был вызван метод получения данных о всех гражданах");
        return personService.getAllPersons(pageNo, pageSize);
    }
    @GetMapping("/person/{id}")
    public ResponseEntity<Optional<PersonModel>> getPersonById(@PathVariable(value = "id") String personId){
        logger.info("Был вызван метод получения данных о гражданине по ID");
        return ResponseEntity.ok().body(personService.getPersonById(personId));
    }
    @PostMapping("/person")
    public void createPerson(@RequestBody PersonModel personModel) {
        logger.info("Был вызван метод добавления нового гражданина");
        personService.createPerson(personModel);
    }
    @PutMapping("/person/{id}")
    public void putPersonById(@PathVariable(value = "id") String personId, @RequestBody PersonModel personModel){
        logger.info("Был вызван метод редактирования данных о гражданине");
        personService.putPersonById(personId,personModel);
    }
    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable(value = "id") String personId) {
        logger.info("Был вызван метод удаления данных о гражданине");
        personService.deletePerson(personId);
    }
    @GetMapping("/verify/{name}/{pass}")
    public ResponseEntity<Boolean> getVerify(@PathVariable(value = "name") String personName, @PathVariable(value = "pass") String personPass) {
        logger.info("Был вызван метод проверки валидности пасспорта и ФИО гражданина");
        return ResponseEntity.ok().body(personService.getVerify(personName, personPass));
    }

}
