package com.supervielle.examen.repositories;

import com.supervielle.examen.model.Persons;
import com.supervielle.examen.model.PersonPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Persons,PersonPk> {
    Optional<Persons> findById(PersonPk personPk);
    Optional<Persons> findByPersonPkDocumentNumber(String documentNumber);
    List<Persons>findAll();

}
