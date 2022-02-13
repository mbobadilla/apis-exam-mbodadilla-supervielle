INSERT INTO document_type (id,type) VALUES(1,'DNI');
INSERT INTO document_type (id,type) VALUES(2,'LE');
INSERT INTO document_type (id,type) VALUES(3,'CI');
INSERT INTO document_type (id,type) VALUES(4,'CUIL');

INSERT INTO country_iso3166_1(code,name) VALUES('032','AR');
INSERT INTO country_iso3166_1(code,name) VALUES('076','BRA');
INSERT INTO country_iso3166_1(code,name) VALUES('026','GBR');





INSERT INTO persons (docTypeId,documentNumber ,countryId ,genre,firstName,lastName) VALUES (1,'30123324','032','F','Lionel','Messi');
INSERT INTO persons (docTypeId,documentNumber ,countryId ,genre,firstName,lastName) VALUES (1,'30123325','032','M','Diego','Maradona');
INSERT INTO persons (docTypeId,documentNumber ,countryId ,genre,firstName,lastName) VALUES (1,'30123326','032','M','Michel','Platini');
INSERT INTO persons (docTypeId,documentNumber ,countryId ,genre,firstName,lastName) VALUES (1,'30123327','032','F','Luis Alberto','Spineta');
INSERT INTO persons (docTypeId,documentNumber ,countryId ,genre,firstName,lastName) VALUES (1,'30123328','032','M','Jorge Luis','Borges');
INSERT INTO persons (docTypeId,documentNumber ,countryId ,genre,firstName,lastName) VALUES (3,'30123330','032','F','Sebastian','Arguello');

INSERT INTO user (username,password) VALUES('lmessi','$2a$10$Dr3h3dx38nAeW2O8orPUoeFQOcdZxsTqvOWbjATs2Cy53u9/U8Be6');
INSERT INTO user (username,password) VALUES('dmaradona','$2a$10$yPT7gvwYIPBZ9WPdbffwn.uGz6kx9SrSeMvHcqMEimtEg3Opn3Np2');

