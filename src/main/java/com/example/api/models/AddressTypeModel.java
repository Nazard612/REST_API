package com.example.api.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "address_type")
public class AddressTypeModel {
    @Id
    @Column(name = "address_type_id")
    @Type(type="pg-uuid")
    private java.util.UUID address_type_id;
    @Column(name = "address_name")
    private String address_name;
    public AddressTypeModel(){

    }
    public AddressTypeModel(String address_name){
        address_type_id= UUID.randomUUID();
        this.address_name=address_name;
    }
    public java.util.UUID getAddress_type_id() {
        return address_type_id;
    }
    public void setAddress_type_id(java.util.UUID address_type_id){
        this.address_type_id=address_type_id;
    }
    public String getAddress_name() {
        return address_name;
    }
    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }
}
