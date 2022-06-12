package com.example.api.models;

import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
@Entity
@Table(name = "person")
public class PersonModel {
    @Id
    @Column(name = "person_id")
    @Type(type="pg-uuid")
    private UUID personId;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "birth_day")
    private LocalDate birthDay;
    @OneToMany(mappedBy = "personModel", cascade = CascadeType.ALL)
    private List<PersonAddress> personAddresses = new ArrayList<>();
    @OneToMany(mappedBy = "personModel", cascade = CascadeType.ALL)
    private List<PersonContact> personContacts = new ArrayList<>();
    @OneToMany(mappedBy = "personModel", cascade = CascadeType.ALL)
    private List<PersonDocument> personDocuments = new ArrayList<>();
    public PersonModel(){}
    public PersonModel(String fullName, LocalDate birthDay){
        personId=UUID.randomUUID();
        this.fullName=fullName;
        this.birthDay=birthDay;
    }
    public UUID getPersonId() {
        return personId;
    }
    public void setPersonId(UUID personId){this.personId=personId;}
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public LocalDate getBirth() {
        return birthDay;
    }
    public void setBirth(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
    public List<PersonAddress> getPersonAddresses(){
        return personAddresses;
    }
    public void setPersonAddresses(List<PersonAddress> personAddresses){
        this.personAddresses = personAddresses;
    }
    public List<PersonContact> getPersonContacts(){
        return personContacts;
    }
    public void setPersonContacts(List<PersonContact> personContacts){
        this.personContacts = personContacts;
    }
    public List<PersonDocument> getPersonDocuments(){
        return personDocuments;
    }
    public void setPersonDocuments(List<PersonDocument> personDocuments){
        this.personDocuments = personDocuments;
    }
}
