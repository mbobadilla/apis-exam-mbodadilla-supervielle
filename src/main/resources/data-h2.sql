INSERT INTO document_type (id,type) VALUES(1,'DNI');
INSERT INTO document_type (id,type) VALUES(2,'LE');
INSERT INTO document_type (id,type) VALUES(3,'CI');
INSERT INTO document_type (id,type) VALUES(4,'CUIL');

INSERT INTO country_iso3166_1(code,name) VALUES('032','AR');
INSERT INTO country_iso3166_1(code,name) VALUES('076','BRA');
INSERT INTO country_iso3166_1(code,name) VALUES('026','GBR');

INSERT INTO document_person(documentNumber,firstName,lastName) VALUES('30123324','Pedro','Navaja');
INSERT INTO document_person(documentNumber,firstName,lastName) VALUES('30123325','Pedro','Navaja');
INSERT INTO document_person(documentNumber,firstName,lastName)  VALUES('30123326','Pedro','Navaja');
INSERT INTO document_person(documentNumber,firstName,lastName) VALUES('30123327','Pedro','Navaja');
INSERT INTO document_person(documentNumber,firstName,lastName) VALUES('30123328','Pedro','Navaja');
INSERT INTO document_person(documentNumber,firstName,lastName) VALUES('30123330','Pedro','Navaja');
INSERT INTO document_person(documentNumber,firstName,lastName) VALUES('30123331','Pedro','Navaja');



INSERT INTO persons (docTypeId,documentNumber ,countryId ,genre) VALUES (1,'30123324','032','F');
INSERT INTO persons (docTypeId,documentNumber ,countryId ,genre) VALUES (1,'30123325','032','M');
INSERT INTO persons (docTypeId,documentNumber ,countryId ,genre) VALUES (1,'30123326','032','M');
INSERT INTO persons (docTypeId,documentNumber ,countryId ,genre) VALUES (1,'30123327','032','F');
INSERT INTO persons (docTypeId,documentNumber ,countryId ,genre) VALUES (1,'30123328','032','M');
INSERT INTO persons (docTypeId,documentNumber ,countryId ,genre) VALUES (3,'30123330','032','F');


