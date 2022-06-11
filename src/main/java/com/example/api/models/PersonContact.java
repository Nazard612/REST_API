package com.example.api.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "contact")
public class PersonContact {
    @Id
    @Column(name = "contact_id")
    @Type(type="pg-uuid")
    private java.util.UUID contact_id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_type_id")
    private ContactTypeModel contact_type_id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id")
    private PersonModel person_id;
    @Column(name = "contact_value")
    private String contact_value;
    public PersonContact(){
        contact_id=UUID.randomUUID();
    }
    public PersonContact(ContactTypeModel contact_type_id, PersonModel person_id, String contact_value){
        contact_id= UUID.randomUUID();
        this.contact_type_id=contact_type_id;
        this.person_id=person_id;
        this.contact_value=contact_value;
    }
    public java.util.UUID getContact_id() {
        return contact_id;
    }
    public void setContact_id(java.util.UUID contact_id){
        this.contact_id=contact_id;
    }

    public void setPerson_id(PersonModel person_id){
        this.person_id=person_id;
    }
    public String getContact_value(){
        return contact_value;
    }
    public void setContact_value(String contact_value){
        this.contact_value=contact_value;
    }
    public ContactTypeModel getContact_type_id(){
        return contact_type_id;
    }
    public void setContact_type_id(ContactTypeModel contact_type_id){
        this.contact_type_id=contact_type_id;
    }
}
