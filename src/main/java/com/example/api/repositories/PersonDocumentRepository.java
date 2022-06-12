package com.example.api.repositories;

import com.example.api.models.DocumentTypeModel;
import com.example.api.models.PersonDocument;
import com.example.api.models.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonDocumentRepository extends JpaRepository<PersonDocument, java.util.UUID> {
    @Query(value = "select personModel from PersonDocument where documentsValue = :val")
    PersonModel findByValue(@Param("val") String val);
    @Query(value = "select documentTypeId from PersonDocument where documentsValue = :val")
    DocumentTypeModel findId(@Param("val") String val);
}
