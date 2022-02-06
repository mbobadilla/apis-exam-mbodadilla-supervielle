package com.supervielle.examen.repositories;

import com.supervielle.examen.model.DocumentPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentPersonRepository  extends JpaRepository<DocumentPerson,String> {
    public Optional<DocumentPerson> findByDocumentNumber(String documentNumber);
}
