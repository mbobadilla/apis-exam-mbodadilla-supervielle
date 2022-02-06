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
@Table(name = "document_type")
public class DocumentType implements Serializable {
    @Id
    private Long id;
    @Column(name="type")
    private String type;

    public DocumentType() {
    }

    public DocumentType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

}
