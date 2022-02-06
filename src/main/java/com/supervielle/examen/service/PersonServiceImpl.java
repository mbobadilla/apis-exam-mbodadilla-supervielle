package com.supervielle.examen.service;
import com.supervielle.examen.exception.NotFoundException;
import com.supervielle.examen.exception.FoundPersonException;
import com.supervielle.examen.model.*;
import com.supervielle.examen.api.v1.request.PersonRequest;
import com.supervielle.examen.repositories.CountryRepository;
import com.supervielle.examen.repositories.DocumentPersonRepository;
import com.supervielle.examen.repositories.DocumentTypeRepository;
import com.supervielle.examen.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.DocumentName;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    PersonRepository personRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    DocumentTypeRepository documentTypeRepository;
    @Autowired
    DocumentPersonRepository documentPersonRepository;

    @Override
    public Persons createPerson(PersonRequest personRequest) throws NotFoundException, FoundPersonException {
        PersonPk pk = getPersonPk(personRequest);
        Optional<Persons> person = personRepository.findById(pk);
        if(!person.isPresent()){
            return personRepository.save(getPerson(personRequest));
        }else{
            throw new FoundPersonException();
        }



    }

    @Override
    public Persons getPersonsById(PersonRequest personRequest) throws NotFoundException {
        PersonPk pk = getPersonPk(personRequest);
        Optional<Persons> person = personRepository.findById(pk);
        if(person.isPresent()){
            return person.get();
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public Persons updatePerson(PersonRequest personRequest,String docNumber) throws NotFoundException, FoundPersonException {
        Optional<DocumentPerson> documentPerson =documentPersonRepository.findByDocumentNumber(docNumber);
        if(documentPerson.isPresent()){
            Persons person =getPerson(personRequest);
            return personRepository.save(person);
        }else{
            throw new NotFoundException();
        }

    }

    @Override
    public void deletePerson(PersonRequest personRequest) throws NotFoundException, FoundPersonException {
        Optional<DocumentPerson> documentPerson =documentPersonRepository.findByDocumentNumber(personRequest.getDocumentNumber());
        if(documentPerson.isPresent()){
            personRepository.delete(getPerson(personRequest));
        }else{
            throw new NotFoundException();
        }


    }
    private Persons getPerson(PersonRequest personRequest) throws FoundPersonException, NotFoundException {
        Persons persons =new Persons(getPersonPk(personRequest));

        return persons;
    }

    private Country getCountry(String name) throws NotFoundException {
        Optional<Country>country=countryRepository.findByName(name);
        if(!country.isPresent()){
            throw new NotFoundException();
        }
        return country.get();

    }

    private DocumentType getDocumentType(String type) throws NotFoundException {
        Optional<DocumentType>documentType=documentTypeRepository.findByType(type);
        if(!documentType.isPresent()){
            throw new NotFoundException();
        }
        return documentType.get();

    }

    private DocumentPerson getDocumentPerson(PersonRequest personRequest)  {
        Optional<DocumentPerson>documentPerson=documentPersonRepository.findByDocumentNumber(personRequest.getDocumentNumber());
        if(!documentPerson.isPresent()){
            return documentPersonRepository.save(new DocumentPerson(personRequest.getDocumentNumber(),personRequest.getFirstName(),personRequest.getLastName()));

        }
        return documentPerson.get();

    }
    PersonPk getPersonPk(PersonRequest personRequest) throws NotFoundException {
        Country country= null;
        DocumentType documentType= null;
        DocumentPerson documentPerson=null;
        try {
            country = getCountry(personRequest.getCountryCode());
            documentType = getDocumentType(personRequest.getDocumentType());

        } catch (NotFoundException e) {
            throw new NotFoundException();
        }
        documentPerson=getDocumentPerson(personRequest);

        return new PersonPk(documentType,documentPerson,country,personRequest.getGenre());
    }

}
