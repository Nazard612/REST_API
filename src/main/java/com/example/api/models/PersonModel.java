package com.example.api.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "person")
public class PersonModel implements Serializable{
    @Id
    @Column(name = "person_id")
    @Type(type="pg-uuid")
    private java.util.UUID person_id;
    @Column(name = "full_name")
    private String full_name;
    @Column(name = "birth_day")
    private LocalDate birth_day;

    @OneToMany(mappedBy = "personModel", cascade = CascadeType.ALL)
    private List<PersonAddress> personAddresses =new ArrayList<>();
    @OneToMany(mappedBy = "person_id", cascade = CascadeType.ALL)
    private List<PersonContact> personContacts =new ArrayList<>();
    @OneToMany(mappedBy = "person_id", cascade = CascadeType.ALL)
    private List<PersonDocument> personDocuments =new ArrayList<>();

    public PersonModel(String full_name, LocalDate birth_day){
        person_id=UUID.randomUUID();
        this.full_name=full_name;
        this.birth_day=birth_day;
    }
    public PersonModel(){

    }
    public java.util.UUID getPerson_id() {
        return person_id;
    }
    public void setPerson_id(java.util.UUID person_id){this.person_id=person_id;}
    public String getFull_name() {
        return full_name;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
    public LocalDate getBirth() {
        return birth_day;
    }
    public void setBirth(LocalDate birth_day) {
        this.birth_day = birth_day;
    }
    public List<PersonAddress> getPerson_addresses(){
        return personAddresses;
    }
    public void setPerson_addresses(List<PersonAddress> personAddresses){
        this.personAddresses = personAddresses;
    }
    public List<PersonContact> getPerson_contacts(){
        return personContacts;
    }
    public void setPerson_contacts(List<PersonContact> personContacts){
        this.personContacts = personContacts;
    }
    public List<PersonDocument> getPerson_documents(){
        return personDocuments;
    }
    public void setPerson_documents(List<PersonDocument> personDocuments){
        this.personDocuments = personDocuments;
    }
}
