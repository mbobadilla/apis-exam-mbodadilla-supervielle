package com.supervielle.examen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supervielle.examen.api.v1.request.PersonRequest;
import com.supervielle.examen.authentication.UserDetailsConfig;
import com.supervielle.examen.config.WebSecurityConfig;
import com.supervielle.examen.exception.ErrorCode;
import com.supervielle.examen.exception.ResourceNotFoundException;
import com.supervielle.examen.model.Country;
import com.supervielle.examen.model.DocumentType;
import com.supervielle.examen.model.PersonPk;
import com.supervielle.examen.model.Persons;
import com.supervielle.examen.repositories.CountryRepository;
import com.supervielle.examen.repositories.DocumentTypeRepository;
import com.supervielle.examen.repositories.PersonRepository;
import com.supervielle.examen.service.PersonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j

@Import(WebSecurityConfig.class)
@AutoConfigureMockMvc(addFilters = false)
public class ExamenApplicationTests {

    @MockBean
    PersonRepository personRepository;

    @MockBean
    CountryRepository countryRepository;

    @MockBean
    DocumentTypeRepository documentTypeRepository;

    @MockBean
    PersonServiceImpl personService;

    @Autowired
    private MockMvc mvc;



    @MockBean
    private UserDetailsConfig userDetailsConfig;
    @MockBean
    private UserDetails userDetails;

    @Test
    void testCreatePersonOk() throws Exception {

        when(personService.createPerson(getPersonRequest())).thenReturn(new Persons());

        mvc.perform(post("/v1/persons/person/create").contentType(MediaType.APPLICATION_JSON).content(getValidJson())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).header("user", "zz").header("password", "xx")).andExpect(status().isCreated());
    }

    @Test
    void testCreatePersonFail() throws Exception {
        when(personService.createPerson(getPersonRequest())).thenThrow(new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND));

        mvc.perform(post("/v1/persons/person/create").contentType(MediaType.APPLICATION_JSON).content(getValidJson())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).header("user", "zz").header("password", "xx")).andExpect(status().is4xxClientError());


    }

    @Test
    void testUpdatePersonFail() throws Exception {
        when(personService.updatePerson(getPersonRequest(),"11111111")).thenReturn(new Persons());
        mvc.perform(post("/v1/persons/person/update").contentType(MediaType.APPLICATION_JSON).content(getValidJson())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).header("user", "zz").header("password", "xx")).andExpect(status().is5xxServerError());


    }



    private String getValidJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonRequest personRequest= new PersonRequest();
        personRequest.setCountryCode("AR");
        personRequest.setDocumentNumber("11111111");
        personRequest.setDocumentType("DNI");
        personRequest.setFirstName("AAA");
        personRequest.setLastName("ZZZ");
        return objectMapper.writeValueAsString(personRequest);
    }

    private String getInValidJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PersonRequest personRequest= new PersonRequest();
        personRequest.setCountryCode("AR");
        personRequest.setDocumentNumber("11111111");
        personRequest.setDocumentType("DNI");
        personRequest.setFirstName("AAA");
        personRequest.setLastName("ZZZ");
        return objectMapper.writeValueAsString("");
    }

    private PersonRequest getPersonRequest(){
        PersonRequest personRequest= new PersonRequest();
        personRequest.setCountryCode("AR");
        personRequest.setDocumentNumber("11111111");
        personRequest.setDocumentType("DNI");
        personRequest.setFirstName("AAA");
        personRequest.setLastName("ZZZ");
        return personRequest;
    }

    private PersonPk getPersonPK() {
        return new PersonPk();
    }

    private Persons getPersons() {
        return new Persons();
    }
}
