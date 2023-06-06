package com.docmanager.docmanagerbackend.documenttypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//this will be an entity for managing the types of documents I will use in my application
//I will use this to create a dropdown menu in the frontend for the user to select the type of document he wants to upload
@Entity(name = "DocumentTypes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DocumentTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
}
