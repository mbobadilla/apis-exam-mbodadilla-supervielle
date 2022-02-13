package com.supervielle.examen.service;

import com.supervielle.examen.api.v1.request.PersonRequest;
import com.supervielle.examen.exception.CustomerNotFoundException;
import com.supervielle.examen.exception.ErrorCode;
import com.supervielle.examen.exception.ResourceNotFoundException;
import com.supervielle.examen.model.Country;
import com.supervielle.examen.model.DocumentType;
import com.supervielle.examen.model.PersonPk;
import com.supervielle.examen.model.Persons;
import com.supervielle.examen.repositories.CountryRepository;
import com.supervielle.examen.repositories.DocumentTypeRepository;
import com.supervielle.examen.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    DocumentTypeRepository documentTypeRepository;


    @Override
    public Persons createPerson(PersonRequest personRequest) {
        Persons persons = getPerson(personRequest);
        return personRepository.save(persons);


    }

    @Override
    public Persons getPersonsById(PersonRequest personRequest) {
        PersonPk pk = getPersonPk(personRequest);
        Optional<Persons> person= personRepository.findById(pk);
        if(person.isPresent()){
            return person.get();
        }else{
            throw new CustomerNotFoundException(ErrorCode.CUSTOMER_NOT_FOUND);
        }

    }

    @Override
    public Persons updatePerson(PersonRequest personRequest, String docNumber) {

       Optional<Persons> persons=personRepository.findByPersonPkDocumentNumber(docNumber);
       if(persons.isPresent()){
           personRepository.delete(persons.get());
           return personRepository.save(getPerson(personRequest));
       }else{
           throw new CustomerNotFoundException(ErrorCode.CUSTOMER_NOT_FOUND);
       }
    }

    @Override
    public void deletePerson(PersonRequest personRequest) {
        Optional<Persons> persons=personRepository.findById(getPersonPk(personRequest));
        if(persons.isPresent()){
            personRepository.delete(persons.get());
        }else{
            throw new CustomerNotFoundException(ErrorCode.CUSTOMER_NOT_FOUND);
        }



    }

    /**
     * build a Persons object from a PersonRequest object
     * @param personRequest
     * @return
     */
    private Persons getPerson(PersonRequest personRequest) {
        PersonPk personPk = getPersonPk(personRequest);
        return new Persons(personPk, personRequest.getFirstName(), personRequest.getLastName());


    }

    /**
     * get a Country code object from your name country
     * @param name
     * @return
     */
    private Country getCountry(String name) {
        Optional<Country> country = countryRepository.findByName(name);
        if (!country.isPresent()) {
            throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND);
        }
        return country.get();

    }

    /**
     * get a DocumentType object from a String type
     * @param type
     * @return
     */
    private DocumentType getDocumentType(String type) {
        Optional<DocumentType> documentType = documentTypeRepository.findByType(type);
        if (!documentType.isPresent()) {
            throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND);
        }
        return documentType.get();

    }

    /**
     * get personPk object by a PersonRequest
     * @param personRequest
     * @return PersonPk
     */

    private PersonPk getPersonPk(PersonRequest personRequest) {
        Country country = getCountry(personRequest.getCountryCode());
        DocumentType documentType = getDocumentType(personRequest.getDocumentType());
        return new PersonPk(documentType, country, personRequest.getDocumentNumber(), personRequest.getGenre());
    }


}
