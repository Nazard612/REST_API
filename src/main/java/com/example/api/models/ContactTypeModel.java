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
    private UUID contactTypeId;
    @Column(name = "contact_name")
    private String contactName;
    public ContactTypeModel(){}
    public ContactTypeModel(String contactName){
        contactTypeId= UUID.randomUUID();
        this.contactName=contactName;
    }
    public UUID getContactTypeId() {
        return contactTypeId;
    }
    public void setContactTypeId(UUID contactTypeId){
        this.contactTypeId=contactTypeId;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

}
