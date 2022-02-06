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


CREATE TABLE document_person(
documentNumber CHAR(15) NOT NULL UNIQUE,
firstName CHAR(20) NOT NULL,
lastName CHAR(30) NOT NULL,
PRIMARY KEY(documentNumber)
);


CREATE TABLE IF NOT EXISTS persons(
  docTypeId int not null,

   FOREIGN KEY (docTypeId )
            REFERENCES document_type(id),

     documentNumber CHAR(15) NOT NULL,

     countryId char(3) NOT NULL,
     FOREIGN KEY (countryId )
                REFERENCES country_iso3166_1(code),

     genre CHAR(1) NOT NULL,

PRIMARY KEY(docTypeId ,documentNumber ,countryId,genre));

CREATE INDEX document_number_id ON persons(documentNumber);

alter table persons add constraint persons_fk_1 FOREIGN KEY(documentNumber) REFERENCES document_person(documentNumber);





