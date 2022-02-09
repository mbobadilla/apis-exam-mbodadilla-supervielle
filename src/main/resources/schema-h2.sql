 CREATE TABLE IF NOT EXISTS country_iso3166_1(
   code CHAR(3) NOT NULL,
   name CHAR(3) NOT NULL,
   PRIMARY KEY(code)
   );

CREATE TABLE IF NOT EXISTS document_type(
 id int NOT NULL,
 type CHAR(8) NOT NULL,
 PRIMARY KEY(id)
 );





    CREATE TABLE IF NOT EXISTS persons(
          docTypeId int not null,

           FOREIGN KEY (docTypeId )
                    REFERENCES document_type(id),

             documentNumber CHAR(15) NOT NULL UNIQUE,

             countryId char(3) NOT NULL,
             FOREIGN KEY (countryId )
                        REFERENCES country_iso3166_1(code),

             genre CHAR(1) NOT NULL,
            firstName VARCHAR(20) NOT NULL,
            lastName VARCHAR(30) NOT NULL,



        PRIMARY KEY(docTypeId ,documentNumber ,countryId,genre));

CREATE INDEX document_number_id ON persons(documentNumber);
CREATE INDEX country_id ON persons(countryId);
CREATE INDEX doctype_id ON persons(docTypeId);

CREATE TABLE IF NOT EXISTS user (
    username VARCHAR(45) NULL,
    password TEXT NULL,
    PRIMARY KEY (username));





