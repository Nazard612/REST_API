package com.example.api.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "document_type")
public class DocumentTypeModel {
    @Id
    @Column(name = "document_type_id")
    @Type(type="pg-uuid")
    private UUID documentTypeId;
    @Column(name = "document_name")
    private String documentName;
    public DocumentTypeModel(){}
    public DocumentTypeModel(String documentName){
        documentTypeId= UUID.randomUUID();
        this.documentName=documentName;
    }
    public UUID getDocumentTypeId() {
        return documentTypeId;
    }
    public void setDocumentTypeId(UUID documentTypeId){
        this.documentTypeId=documentTypeId;
    }
    public String getDocumentName() {
        return documentName;
    }
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
}
