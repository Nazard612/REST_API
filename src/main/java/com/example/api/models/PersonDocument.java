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
    private java.util.UUID documents_id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_type_id")
    private DocumentTypeModel document_type_id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id")
    private PersonModel person_id;
    @Column(name = "documents_value")
    private String documents_value;
    public PersonDocument(){
        documents_id= UUID.randomUUID();
    }
    public PersonDocument(DocumentTypeModel document_type_id, PersonModel person_id, String documents_value){
        documents_id= UUID.randomUUID();
        this.document_type_id=document_type_id;
        this.person_id=person_id;
        this.documents_value=documents_value;
    }
    public java.util.UUID getDocuments_id() {
        return documents_id;
    }
    public void setDocuments_id(java.util.UUID documents_id){
        this.documents_id=documents_id;
    }
    public void setPerson_id(PersonModel person_id){
        this.person_id=person_id;
    }
    public String getDocuments_value(){
        return documents_value;
    }
    public void setDocuments_value(String documents_value){
        this.documents_value=documents_value;
    }
    public DocumentTypeModel getDocument_type_id(){
        return document_type_id;
    }
    public void setDocument_type_id(DocumentTypeModel document_type_id){
        this.document_type_id=document_type_id;
    }
}
