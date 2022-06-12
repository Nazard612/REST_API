package com.example.api.repositories;

import com.example.api.models.AddressTypeModel;
import com.example.api.models.ContactTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactTypeRepository extends JpaRepository<ContactTypeModel, java.util.UUID> {
    @Query("select c from ContactTypeModel c where c.contactName=?1")
    ContactTypeModel findByType(String type);
}
