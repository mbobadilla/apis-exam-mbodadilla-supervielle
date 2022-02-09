package com.supervielle.examen.service;

import com.supervielle.examen.model.Persons;
import com.supervielle.examen.api.v1.request.PersonRequest;
import org.springframework.stereotype.Service;


public interface PersonService {
    public Persons createPerson(PersonRequest personRequest) ;
    public Persons getPersonsById(PersonRequest personRequest) ;
    public Persons updatePerson(PersonRequest personRequest,String docNumber) ;
    public void deletePerson(PersonRequest personRequest) ;
}
