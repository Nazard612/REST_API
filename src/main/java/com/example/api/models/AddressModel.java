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
    private UUID addressId;
    @Column(name = "address_value")
    private String addressValue;
    public AddressModel(){}
    public AddressModel(String addressValue){
        addressId= UUID.randomUUID();
        this.addressValue=addressValue;
    }
    public UUID getAddress_id() {
        return addressId;
    }
    public void setAddress_id(UUID addressId) {
        this.addressId=addressId;
    }
    public String getAddress_value() {
        return addressValue;
    }
    public void setAddress_value(String addressValue) {
        this.addressValue = addressValue;
    }
}
