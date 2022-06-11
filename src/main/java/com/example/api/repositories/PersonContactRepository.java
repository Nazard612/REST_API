package com.example.api.repositories;

import com.example.api.models.PersonContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonContactRepository extends JpaRepository<PersonContact, java.util.UUID> {

}
