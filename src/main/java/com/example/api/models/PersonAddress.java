package com.example.api.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "person_address")
public class PersonAddress implements Serializable {
    @Id
    @Column(name = "person_address_id")
    @Type(type="pg-uuid")
    private java.util.UUID person_address_id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressModel address_name_id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_type_id")
    private AddressTypeModel address_type_id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id")
    private PersonModel personModel;
    public PersonAddress(){
        person_address_id = UUID.randomUUID();
    }
    public PersonAddress(AddressModel address_name_id, AddressTypeModel address_type_id, PersonModel personModel){
        person_address_id = UUID.randomUUID();
        this.address_name_id=address_name_id;
        this.address_type_id=address_type_id;
        this.personModel = personModel;
    }
    public java.util.UUID getPerson_address_id() {
        return person_address_id;
    }
    public void setPerson_address_id(java.util.UUID person_address_id){
        this.person_address_id=person_address_id;
    }
    public void setPerson_model(PersonModel personModel){
        this.personModel = personModel;
    }
    public AddressModel getAddress_name_id(){
        return address_name_id;
    }
    public void setAddress_name_id(AddressModel address_name_id){
        this.address_name_id=address_name_id;
    }
    public AddressTypeModel getAddress_type_id(){
        return address_type_id;
    }
    public void setAddress_type_id(AddressTypeModel address_type_id){
        this.address_type_id=address_type_id;
    }
}
