package com.example.api.repositories;

import com.example.api.models.AddressTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressTypeRepository extends JpaRepository<AddressTypeModel, UUID> {

}
