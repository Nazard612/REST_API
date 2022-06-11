package com.example.api.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "contact_type")
public class ContactTypeModel {
    @Id
    @Column(name = "contact_type_id")
    @Type(type="pg-uuid")
    private java.util.UUID contact_type_id;
    @Column(name = "contact_name")
    private String contact_name;
    public ContactTypeModel(){

    }
    public ContactTypeModel(String contact_name){
        contact_type_id= UUID.randomUUID();
        this.contact_name=contact_name;
    }
    public java.util.UUID getContact_type_id() {
        return contact_type_id;
    }
    public void setContact_type_id(java.util.UUID contact_type_id){
        this.contact_type_id=contact_type_id;
    }
    public String getContact_name() {
        return contact_name;
    }
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

}
