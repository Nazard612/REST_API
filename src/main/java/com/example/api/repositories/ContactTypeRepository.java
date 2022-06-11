package com.example.api.repositories;

import com.example.api.models.ContactTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactTypeRepository extends JpaRepository<ContactTypeModel, java.util.UUID> {

}
