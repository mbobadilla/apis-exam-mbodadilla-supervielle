package com.supervielle.examen.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "document_person")
public class DocumentPerson implements Serializable {
    @Id
    @Column(name="DOCUMENTNUMBER")
    private String documentNumber;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;

    public DocumentPerson() {
    }

    public DocumentPerson(String documentNumber, String firstName, String lastName) {
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
