package com.example.api.repositories;

import com.example.api.models.AddressTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AddressTypeRepository extends JpaRepository<AddressTypeModel, UUID> {
    @Query("select a from AddressTypeModel a where a.addressName=?1")
    AddressTypeModel findByType(String type);
}
