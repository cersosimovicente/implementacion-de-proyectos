## Lab 1 - Spring Boot
- En este ejercicio creará una aplicación Spring Boot sencilla. Si cree que ya comprende bien Spring Boot, puede omitir este ejercicio. Todos los demás ejercicios de este curso asumirán que conoce Spring Boot

**Parte 1: Aplicación web sencilla**
1. Utilizando [https://start.spring.io](https://start.spring.io), cree un nuevo proyecto Spring Boot.
  - Utilice Maven. Todas las instrucciones de laboratorio se basan en Maven.
  - Utilice las últimas versiones estables de Boot y Java. Estas instrucciones se probaron originalmente con Java 1.8, Boot 2.0.3.RELEASE.
  - Utilice el empaquetado JAR por ahora, a menos que prefiera WAR y ya tenga un servidor local (como Tomcat) instalado y listo para ejecutarlo.
  - Utilice los valores que desee para grupo, artefacto, paquete, descripción, etc.
  - Seleccione las siguientes dependencias: Web, Thymeleaf, JPA, HSQLDB y Actuator.
    
2. Cree un nuevo controlador en el paquete base:
  - Nombra el controlador como quieras.
  - Anotar el controlador con @Controller.

3. Cree un nuevo método en el controlador:
  - Nombra el método como quieras. Haga que devuelva una cadena. No se necesitan parámetros.
  - Anotar el método con @GetMapping("/")
  - Haga que el método devuelva el valor de cadena "hola".

4. Si aún no está presente, cree una nueva carpeta en src/main/resources llamada "templates"

5. Cree un nuevo archivo en la carpeta de plantillas llamado "hello.html". Coloque las palabras "Hola desde Thymeleaf" (o cualquier etiqueta que desee) dentro del archivo.

6. Guarde todo su trabajo. Ejecute su aplicación.
  - Si está trabajando en Spring Tool Suite, simplemente haga clic derecho en la aplicación/Ejecutar como/Aplicación Spring Boot.
  - Si está trabajando en otro IDE, ejecute el método principal que se encuentra dentro de la clase de Aplicación principal.
  - Si desea ejecutar desde un símbolo del sistema, desde la carpeta raíz de la aplicación ejecute mvn spring-boot:run.
  
7. Abra un navegador en [http://localhost:8080/](http://localhost:8080/). Deberías ver tu página web.
