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
    private UUID contactId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_type_id")
    private ContactTypeModel contactTypeId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id")
    private PersonModel personModel;
    @Column(name = "contact_value")
    private String contactValue;
    public PersonContact(){
        contactId=UUID.randomUUID();
    }
    public PersonContact(ContactTypeModel contactTypeId, PersonModel personModel, String contactValue){
        contactId= UUID.randomUUID();
        this.contactTypeId=contactTypeId;
        this.personModel=personModel;
        this.contactValue=contactValue;
    }
    public UUID getContactId() {
        return contactId;
    }
    public void setContactId(UUID contactId){
        this.contactId=contactId;
    }
    public void setPersonModel(PersonModel personModel){
        this.personModel=personModel;
    }
    public String getContactValue(){
        return contactValue;
    }
    public void setContactValue(String contactValue){
        this.contactValue=contactValue;
    }
    public ContactTypeModel getContactTypeId(){
        return contactTypeId;
    }
    public void setContactTypeId(ContactTypeModel contactTypeId){
        this.contactTypeId=contactTypeId;
    }
}
