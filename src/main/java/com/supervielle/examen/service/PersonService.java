package com.supervielle.examen.service;

import com.supervielle.examen.exception.FoundPersonException;
import com.supervielle.examen.exception.NotFoundException;
import com.supervielle.examen.model.Persons;
import com.supervielle.examen.api.v1.request.PersonRequest;

import java.util.List;

public interface PersonService {
    public Persons createPerson(PersonRequest personRequest) throws NotFoundException, FoundPersonException;
    public Persons getPersonsById(PersonRequest personRequest) throws NotFoundException;
    public Persons updatePerson(PersonRequest personRequest,String docNumber) throws NotFoundException, FoundPersonException;
    public void deletePerson(PersonRequest personRequest) throws NotFoundException, FoundPersonException;
}
