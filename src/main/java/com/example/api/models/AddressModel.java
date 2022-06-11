package com.example.api.models;
import javax.persistence.*;

import org.hibernate.annotations.Type;

import java.util.*;

@Entity
@Table(name = "address")
public class AddressModel {
    @Id
    @Column(name = "address_id")
    @Type(type="pg-uuid")
    private java.util.UUID address_id;
    @Column(name = "address_value")
    private String address_value;
    public AddressModel(){

    }
    public AddressModel(String address_value){
        address_id= UUID.randomUUID();
        this.address_value=address_value;
    }
    public java.util.UUID getAddress_id() {
        return address_id;
    }
    public void setAddress_id(java.util.UUID address_id) {
        this.address_id=address_id;
    }
    public String getAddress_value() {
        return address_value;
    }
    public void setAddress_value(String address_value) {
        this.address_value = address_value;
    }
}
