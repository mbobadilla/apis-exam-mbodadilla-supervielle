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



    public Persons() {
    }

    public Persons(PersonPk personPk) {
        this.personPk = personPk;

    }

}
