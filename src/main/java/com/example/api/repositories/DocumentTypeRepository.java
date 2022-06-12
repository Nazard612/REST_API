package com.example.api.repositories;

import com.example.api.models.ContactTypeModel;
import com.example.api.models.DocumentTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DocumentTypeRepository extends JpaRepository<DocumentTypeModel, java.util.UUID> {
    @Query("select d from DocumentTypeModel d where d.documentName=?1")
    DocumentTypeModel findByType(String type);
}
