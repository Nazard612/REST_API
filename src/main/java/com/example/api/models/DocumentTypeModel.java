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
    private java.util.UUID document_type_id;
    @Column(name = "document_name")
    private String document_name;
    public DocumentTypeModel(){

    }
    public DocumentTypeModel(String document_name){
        document_type_id= UUID.randomUUID();
        this.document_name=document_name;
    }
    public java.util.UUID getDocument_type_id() {
        return document_type_id;
    }
    public void setDocument_type_id(java.util.UUID document_type_id){
        this.document_type_id=document_type_id;
    }
    public String getDocument_name() {
        return document_name;
    }
    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }
}
