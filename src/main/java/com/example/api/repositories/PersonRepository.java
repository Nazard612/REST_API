package com.example.api.repositories;

import com.example.api.models.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PersonRepository extends JpaRepository<PersonModel, java.util.UUID> {
    @Query("select p.personId from PersonModel p where p.fullName=?1")
    UUID findByFull_name(String fullName);
}
