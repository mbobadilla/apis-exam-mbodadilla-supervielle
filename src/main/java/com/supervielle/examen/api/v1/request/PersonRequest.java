package com.supervielle.examen.api.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Credit cards", description = "Credit cards")
public class PersonRequest {

    private String documentType;
    private String documentNumber;
    private String countryCode;
    private String genre;
    private String firstName;
    private String lastName;
}
