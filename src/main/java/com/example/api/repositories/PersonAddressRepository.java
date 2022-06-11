package com.example.api.repositories;

import com.example.api.models.PersonAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonAddressRepository extends JpaRepository<PersonAddress, UUID> {

}
