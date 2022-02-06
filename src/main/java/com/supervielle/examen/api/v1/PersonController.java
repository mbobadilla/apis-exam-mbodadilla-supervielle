package com.supervielle.examen.api.v1;

import com.supervielle.examen.exception.CustomErrorResponse;
import com.supervielle.examen.exception.FoundPersonException;
import com.supervielle.examen.exception.NotFoundException;
import com.supervielle.examen.model.Persons;

import com.supervielle.examen.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.supervielle.examen.api.v1.request.PersonRequest;
import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Person resource", description = "Supervielle Person")
@RestController

public class PersonController {

    @Autowired
    PersonService personService;
    @PostMapping(value = "/persons/person/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // https://www.dariawan.com/tutorials/spring/documenting-spring-boot-rest-api-springdoc-openapi-3/
    @Operation(summary = "Create a Person")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Person create", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Persons.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Invalid paremeter supplied", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Unexpected system error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))})})

    public ResponseEntity<Persons> createPerson(@RequestBody PersonRequest personRequest) throws NotFoundException{
        try {
            return new  ResponseEntity(personService.createPerson(personRequest),HttpStatus.CREATED);
        } catch (FoundPersonException e) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }

    @GetMapping(value = "/persons/person",produces = MediaType.APPLICATION_JSON_VALUE)
    // https://www.dariawan.com/tutorials/spring/documenting-spring-boot-rest-api-springdoc-openapi-3/
    @Operation(summary = "Get creditcards by type and document or cuit", parameters = {
            @Parameter(in = ParameterIn.QUERY, description = "property filters, selects properties of an object using a subset of the Facebook Graph API filtering syntax", name = "fields", required = false, example = "*", content = @Content(schema = @Schema(type = "String")))})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the creditcards", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Persons.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Invalid paremeter supplied", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Credit cards not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Unexpected system error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))})})

    public ResponseEntity<Persons> getPersonById(@Valid @RequestBody PersonRequest personRequest){

        try {
            return ResponseEntity.ok(personService.getPersonsById(personRequest));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping (value="/persons/person/update",produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update person by document number")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the creditcards", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Persons.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Invalid paremeter supplied", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Credit cards not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Unexpected system error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))})})

    public ResponseEntity<?> updatePerson(@RequestParam(value = "docNumber",required = true) String docNumber,  @RequestBody PersonRequest personRequest) throws FoundPersonException {

        try {
            return ResponseEntity.ok(personService.updatePerson(personRequest,docNumber));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value ="/persons/person/delete" ,produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
    // https://www.dariawan.com/tutorials/spring/documenting-spring-boot-rest-api-springdoc-openapi-3/
    @Operation(summary = "Delete Person by Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Person deleted", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Persons.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Invalid paremeter supplied", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Person not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Unexpected system error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorResponse.class))})})


    public ResponseEntity<?> deletePersonById(@Valid @RequestBody PersonRequest personRequest) throws  FoundPersonException {
        try {
            personService.deletePerson(personRequest);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
