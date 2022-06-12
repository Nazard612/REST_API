package com.example.api.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "documents")
public class PersonDocument {
    @Id
    @Column(name = "documents_id")
    @Type(type="pg-uuid")
    private UUID documentsId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_type_id")
    private DocumentTypeModel documentTypeId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id")
    private PersonModel personModel;
    @Column(name = "documents_value")
    private String documentsValue;
    public PersonDocument(){
        documentsId= UUID.randomUUID();
    }
    public PersonDocument(DocumentTypeModel documentTypeId, PersonModel personModel, String documentsValue){
        documentsId= UUID.randomUUID();
        this.documentTypeId=documentTypeId;
        this.personModel=personModel;
        this.documentsValue=documentsValue;
    }
    public UUID getDocumentsId() {
        return documentsId;
    }
    public void setDocumentsId(UUID documentsId){
        this.documentsId=documentsId;
    }
    public void setPersonModel(PersonModel personModel){
        this.personModel=personModel;
    }
    public String getDocumentsValue(){
        return documentsValue;
    }
    public void setDocumentsValue(String documentsValue){
        this.documentsValue=documentsValue;
    }
    public DocumentTypeModel getDocumentTypeId(){
        return documentTypeId;
    }
    public void setDocumentTypeId(DocumentTypeModel documentTypeId){
        this.documentTypeId=documentTypeId;
    }
}
