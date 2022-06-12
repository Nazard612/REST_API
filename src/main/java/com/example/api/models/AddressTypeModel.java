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
    private UUID addressTypeId;
    @Column(name = "address_name")
    private String addressName;
    public AddressTypeModel(){}
    public AddressTypeModel(String addressName){
        addressTypeId = UUID.randomUUID();
        this.addressName = addressName;
    }
    public UUID getAddressTypeId() {
        return addressTypeId;
    }
    public void setAddressTypeId(UUID addressTypeId){
        this.addressTypeId=addressTypeId;
    }
    public String getAddressName() {
        return addressName;
    }
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
}
