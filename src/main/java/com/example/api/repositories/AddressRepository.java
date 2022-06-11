package com.example.api.repositories;

import com.example.api.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressModel, java.util.UUID> {

}
