| Funcionalidad                    | MySQL                                            | PostgreSQL                                       |
|----------------------------------|--------------------------------------------------|--------------------------------------------------|
| Crear base de datos              | `CREATE DATABASE nombre;`                        | `CREATE DATABASE nombre;`                        |
| Borrar base de datos             | `DROP DATABASE nombre;`                          | `DROP DATABASE nombre;`                          |
| Seleccionar base de datos        | `USE nombre;`                                    | `\c nombre`                                      |
| Crear tabla                      | `CREATE TABLE nombre (...);`                     | `CREATE TABLE nombre (...);`                     |
| Borrar tabla                     | `DROP TABLE nombre;`                             | `DROP TABLE nombre;`                             |
| Insertar datos                   | `INSERT INTO tabla (col1, col2) VALUES (v1, v2);`| `INSERT INTO tabla (col1, col2) VALUES (v1, v2);`|
| Seleccionar datos                | `SELECT * FROM tabla;`                           | `SELECT * FROM tabla;`                           |
| Actualizar datos                 | `UPDATE tabla SET col = val WHERE cond;`         | `UPDATE tabla SET col = val WHERE cond;`         |
| Borrar datos                     | `DELETE FROM tabla WHERE cond;`                  | `DELETE FROM tabla WHERE cond;`                  |
| Crear índice                     | `CREATE INDEX idx ON tabla (col);`               | `CREATE INDEX idx ON tabla (col);`               |
| Borrar índice                    | `DROP INDEX idx ON tabla;`                       | `DROP INDEX idx;`                                |
| Crear vista                      | `CREATE VIEW vista AS SELECT ...;`               | `CREATE VIEW vista AS SELECT ...;`               |
| Borrar vista                     | `DROP VIEW vista;`                               | `DROP VIEW vista;`                               |
| Añadir columna                   | `ALTER TABLE tabla ADD COLUMN col tipo;`         | `ALTER TABLE tabla ADD COLUMN col tipo;`         |
| Borrar columna                   | `ALTER TABLE tabla DROP COLUMN col;`             | `ALTER TABLE tabla DROP COLUMN col;`             |
| Cambiar tipo de columna          | `ALTER TABLE tabla MODIFY col nuevo_tipo;`       | `ALTER TABLE tabla ALTER COLUMN col TYPE nuevo_tipo;`|
| Renombrar tabla                  | `ALTER TABLE tabla RENAME TO nuevo_nombre;`      | `ALTER TABLE tabla RENAME TO nuevo_nombre;`      |
| Renombrar columna                | `ALTER TABLE tabla CHANGE col nuevo_nombre tipo;`| `ALTER TABLE tabla RENAME COLUMN col TO nuevo_nombre;`|
| Transacciones                    | `START TRANSACTION; ... COMMIT;`                 | `BEGIN; ... COMMIT;`                             |
| Commit transacción               | `COMMIT;`                                        | `COMMIT;`                                        |
| Rollback transacción             | `ROLLBACK;`                                      | `ROLLBACK;`                                      |
| Obtener estructura de tabla      | `DESCRIBE tabla;`                                | `\d tabla`                                       |
| Obtener lista de tablas          | `SHOW TABLES;`                                   | `\dt`                                            |
| Obtener lista de bases de datos  | `SHOW DATABASES;`                                | `\l`                                             |
| Crear secuencia                  | `CREATE SEQUENCE seq;`                           | `CREATE SEQUENCE seq;`                           |
| Usar secuencia                   | `SELECT NEXTVAL(seq);`                           | `SELECT NEXTVAL('seq');`                         |
| Comentarios                      | `-- comentario` o `# comentario`                 | `-- comentario`                                  |
| Función de cadena de texto       | `CONCAT(str1, str2)`                             | `||` (concatenation operator) o `CONCAT(str1, str2)`|

