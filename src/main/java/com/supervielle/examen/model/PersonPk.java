package com.supervielle.examen.model;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@Embeddable
public class PersonPk implements Serializable {



    @OneToOne
    @JoinColumn(name = "DOCTYPEID")
    private DocumentType documentTypeId;
    @OneToOne
    @JoinColumn(name = "COUNTRYID")
    private  Country countryCode;
    @Column(name="DOCUMENTNUMBER")
    private String documentNumber;
    @Column(name="GENRE")
    private String genre;

    public PersonPk() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonPk personPk = (PersonPk) o;
        return Objects.equals(documentTypeId, personPk.documentTypeId) && Objects.equals(countryCode, personPk.countryCode) && Objects.equals(documentNumber, personPk.documentNumber) && Objects.equals(genre, personPk.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentTypeId, countryCode, documentNumber, genre);
    }

    public PersonPk(DocumentType documentTypeId, Country countryCode, String documentNumber, String genre) {
        this.documentTypeId = documentTypeId;
        this.countryCode = countryCode;
        this.documentNumber = documentNumber;
        this.genre = genre;
    }


}
