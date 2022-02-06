package com.supervielle.examen.repositories;

import com.supervielle.examen.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentTypeRepository extends JpaRepository<DocumentType,String> {
    Optional<DocumentType> findByType(String type);
}
