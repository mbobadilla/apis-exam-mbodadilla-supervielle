package com.supervielle.examen.api.v1;


import com.supervielle.examen.model.Persons;
import com.supervielle.examen.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "User resource", description = "Supervielle User")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/users/user/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    @Operation(summary = "Create a User")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "User create", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Persons.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Invalid paremeter supplied", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Unexpected system error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))})})

    public ResponseEntity<?> createPerson(@RequestParam(value = "username", required = true) String username, @RequestParam(value = "password", required = true) String password) {
        return ResponseEntity.ok(userService.addUser(username, password));
    }


    @PostMapping(value = "/users/user/auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    @Operation(summary = "Authenticate a User")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "User authenticate", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Persons.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Invalid paremeter supplied", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Unexpected system error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))})})

    public ResponseEntity<?> authPerson(@RequestParam(value = "username", required = true) String username, @RequestParam(value = "password", required = true) String password) {
        return ResponseEntity.ok(userService.addUser(username, password));
    }
}
