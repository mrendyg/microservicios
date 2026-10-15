INSERT INTO `asignaturas` (`id`, `nombre`, `padre_id`) VALUES
	(1, 'Matemáticas', NULL),
	(2, 'Lenguaje', NULL),
	(3, 'Inglés', NULL),
	(4, 'Ciencias Naturales', NULL),
	(5, 'Ciencias Sociales y Historia', NULL),
	(6, 'Música', NULL),
	(7, 'Artes', NULL),
	(8, 'Algebra', 1),
	(9, 'Aritmética', 1),
	(10, 'Trigonometría', 1),
	(11, 'Lectura y comprensión', 2),
	(12, 'Verbos', 2),
	(13, 'Gramática', 2),
	(14, 'Inglés', 3),
	(15, 'Gramática', 3),
	(16, 'Verbos', 3),
	(17, 'Ciencias Naturales', 4),
	(18, 'Biología', 4),
	(19, 'Física', 4),
	(20, 'Quimica', 4),
	(21, 'Historia', 5),
	(22, 'Ciencias Sociales', 5),
	(23, 'Filosofía', 5),
	(24, 'Música', 6),
	(25, 'Artes', 7);


INSERT INTO alumnos (id, nombre, apellido, email) VALUES
(2, 'Jose', 'Ortega', 'jortega@correo.cl'),
(3, 'Pablo', 'Mesa', 'pmesa@correo.cl'),
(4, 'Camilo', 'Flores', 'cflores@correo.cl'),
(5, 'Pedro', 'Gomez', 'pgomez@correo.cl'),
(6, 'Gustavo', 'Canarios', 'gcanarios@correo.cl'),
(7, 'Camilo', 'Flores', 'cflores@correo.cl'),
(8, 'Carla', 'Gonzales', 'cgonzales@correo.cl'),
(9, 'Carolina', 'Gonzales', 'cagonzales@correo.cl'),
(10, 'Jorge', 'Mendoza', 'cflores@correo.cl');

INSERT INTO cursos (id, nombre) VALUES
(2, 'Segundo basico A'),
(3, 'Tercero basico A'),
(4, 'Cuarto basico A'),
(5, 'Quinto basico A');

INSERT INTO cursos_alumnos (alumnos_id, curso_id) VALUES
(2, 2),
(3, 2),
(4, 2),
(5, 3),
(6, 4),
(7, 4),
(8, 5),
(9, 5),
(10, 3);

INSERT INTO examenes (id, nombre, asignatura_id) VALUES
(1, 'Matemáticas', 1),
(2, 'Compresion Lectora', 2),
(3, 'Historia', 5);

INSERT INTO preguntas (id, texto, examen_id) VALUES
(1, 'Quien descubrio america?', 3),
(2, 'Como se descubrio america?', 3),
(3, 'Cuando se descubrio america?', 3);

INSERT INTO cursos_examenes (curso_id, examenes_id, examen_id) VALUES
(1, 1, 1),
(2, 2, 2);
