package com.example.api.models;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "person_address")
public class PersonAddress {
    @Id
    @Column(name = "person_address_id")
    @Type(type="pg-uuid")
    private UUID personAddressId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressModel addressNameId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_type_id")
    private AddressTypeModel addressTypeId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id")
    private PersonModel personModel;
    public PersonAddress(){
        personAddressId = UUID.randomUUID();
    }
    public PersonAddress(AddressModel addressNameId, AddressTypeModel addressTypeId, PersonModel personModel){
        personAddressId = UUID.randomUUID();
        this.addressNameId=addressNameId;
        this.addressTypeId=addressTypeId;
        this.personModel = personModel;
    }
    public UUID getPersonAddressId() {
        return personAddressId;
    }
    public void setPersonAddressId(java.util.UUID personAddressId){
        this.personAddressId=personAddressId;
    }
    public void setPersonModel(PersonModel personModel){
        this.personModel = personModel;
    }
    public AddressModel getAddressNameId(){
        return addressNameId;
    }
    public void setAddressNameId(AddressModel addressNameId){
        this.addressNameId=addressNameId;
    }
    public AddressTypeModel getAddressTypeId(){
        return addressTypeId;
    }
    public void setAddressTypeId(AddressTypeModel addressTypeId){
        this.addressTypeId=addressTypeId;
    }
}
