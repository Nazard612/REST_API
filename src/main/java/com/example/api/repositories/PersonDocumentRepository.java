package com.example.api.repositories;

import com.example.api.models.DocumentTypeModel;
import com.example.api.models.PersonDocument;
import com.example.api.models.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonDocumentRepository extends JpaRepository<PersonDocument, java.util.UUID> {
    @Query(value = "select person_id from PersonDocument where documents_value = :val")
    PersonModel findByValue(@Param("val") String val);
    @Query(value = "select document_type_id from PersonDocument where documents_value = :val")
    DocumentTypeModel findId(@Param("val") String val);
}
