package com.supervielle.examen.model;


import lombok.Getter;
import lombok.Setter;



import javax.persistence.*;

import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "persons")
public class Persons implements Serializable {

    @EmbeddedId
    private PersonPk personPk;
    @Column(name ="FIRSTNAME")
    private String firstName;
    @Column(name ="LASTNAME")
    private String lastName;



    public Persons() {
    }



    public Persons(PersonPk personPk,  String firstName, String lastName) {
        this.personPk = personPk;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
