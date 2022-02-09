package com.supervielle.examen.repositories;

import com.supervielle.examen.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType,String> {
    Optional<DocumentType> findByType(String type);
}
