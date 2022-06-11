package com.example.api.repositories;

import com.example.api.models.DocumentTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeRepository extends JpaRepository<DocumentTypeModel, java.util.UUID> {}
