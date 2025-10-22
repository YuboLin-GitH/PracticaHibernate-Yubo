DROP DATABASE IF EXISTS pelicula;
CREATE DATABASE pelicula;
USE pelicula;

CREATE TABLE pelicula (
  id INT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(50) DEFAULT NULL,
  fecha DATE DEFAULT NULL,
  director VARCHAR(50) DEFAULT NULL,
  genero VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO pelicula (id, titulo, fecha, director, genero) VALUES
(1, 'El sexto sentido', STR_TO_DATE('14-01-2000', '%d-%m-%Y'), 'M. Night Shyamalan', 'Drama'),
(2, 'Pulp Fiction', STR_TO_DATE('13-01-1995', '%d-%m-%Y'), 'Tarantino', 'Acción'),
(3, 'Todo Sobre Mi Madre', STR_TO_DATE('08-04-1999', '%d-%m-%Y'), 'Almodobar', 'Drama'),
(4, '300', STR_TO_DATE('09-03-2007', '%d-%m-%Y'), 'Zack Snyder', 'Acción'),
(5, 'El silencio de los corderos', STR_TO_DATE('06-09-1991', '%d-%m-%Y'), 'Jonathan Demme', 'Drama'),
(6, 'Forrest Gump', STR_TO_DATE('23-09-1994', '%d-%m-%Y'), 'Robert Zemeckis', 'Comedia'),
(7, 'Las Hurdes', STR_TO_DATE('01-12-1933', '%d-%m-%Y'), 'Luis Buñuel', 'Documental');