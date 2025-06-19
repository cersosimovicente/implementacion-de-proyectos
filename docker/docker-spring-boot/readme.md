# 1. Crear el proyecto Spring Boot
Puedes usar Spring Initializr:
- Project: Maven
- Language: Java
- Spring Boot: 3.x.x (estable)
- Group: com.ejemplo
- Artifact: demo
- Dependencies:
  - Spring Web
  - Spring Data JPA
  - MySQL Driver
- Descarga y descomprime el proyecto.
# 2. Configura application.properties
Edita src/main/resources/application.properties:
```yml
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```
Crea la base de datos en MySQL:

CREATE DATABASE tu_base_de_datos;

# 3. Crea la entidad
```java
package com.ejemplo.demo.modelo;

import jakarta.persistence.*;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;

    // Getters y setters
}
```

# 4. Crea el repositorio

src/main/java/com/ejemplo/demo/repositorio/PersonaRepositorio.

```java
package com.ejemplo.demo.repositorio;

import com.ejemplo.demo.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepositorio extends JpaRepository<Persona, Long> {}
```
# 5. Crea el controlador
src/main/java/com/ejemplo/demo/controlador/PersonaControlador.java
```
package com.ejemplo.demo.controlador;

import com.ejemplo.demo.modelo.Persona;
import com.ejemplo.demo.repositorio.PersonaRepositorio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaControlador {

    private final PersonaRepositorio personaRepositorio;

    public PersonaControlador(PersonaRepositorio personaRepositorio) {
        this.personaRepositorio = personaRepositorio;
    }

    @GetMapping
    public List<Persona> obtenerPersonas() {
        return personaRepositorio.findAll();
    }

    @PostMapping
    public Persona crearPersona(@RequestBody Persona persona) {
        return personaRepositorio.save(persona);
    }
}
```
# 6. Ejecuta tu aplicación
./mvnw spring-boot:run

http://localhost:8080/api/personas

# 1. Dockerfile para Spring Boot
```Dockerfile
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```
Asegúrate de que tu jar se llame igual que en la línea COPY.

# 2. docker-compose.yml

Crea un archivo docker-compose.yml para levantar tanto la app como MySQL:
```yaml
version: '3.8'
services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_DATABASE: tu_base_de_datos
      MYSQL_ROOT_PASSWORD: tu_contraseña
    ports:
      - "3306:3306"
    volumes:
      - db-data:/var/lib/mysql
    restart: always

  app:
    build: .
    depends_on:
      - mysql
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/tu_base_de_datos?serverTimezone=UTC
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: tu_contraseña
    ports:
      - "8080:8080"
    restart: always

volumes:
  db-data:
```
# 3. Cambios en application.properties
```properties
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

# 4. Construir y levantar
./mvnw clean package
Luego, desde la carpeta donde tienes el Dockerfile y docker-compose.yml:
docker-compose up --build









