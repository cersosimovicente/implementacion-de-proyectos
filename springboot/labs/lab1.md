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

  **Parte 2 - Return a RESTful Response**
  9. Cree una nueva clase Java llamada "Team" en el paquete base. Asígnele un campo Long para identificación y campos de Cadena para nombre, ubicación y mascota (o cualquier otra propiedad que desee). Genere "getters and setters" para todos los campos. Guarda tu trabajo.
  
10. Cree un nuevo controlador llamado "TeamController". Anótelo con @RestController.
11. Cree un nuevo método en TeamController.
  - Nombra el método "getTeams". Haga que devuelva una Lista de objetos del tipo Team.
  - Anotar el método con @GetMapping("/teams")
  - Haga que el método cree una Lista de objetos de Team. Cree uno o más objetos de team y agréguelos a la lista. Establezca las propiedades de los equipos con los valores que desee y devuelva la Lista. Ejemplo:

  ```java
	@GetMapping("/teams")
	public List<Team> getTeams() {
		List<Team> list = new ArrayList<>();

		Team team = new Team();
		team.setId(0l);
		team.setLocation("Harlem");
		team.setName("Globetrotters");
		list.add(team);
		
		team = new Team();
		team.setId(1l);
		team.setLocation("Washington");
		team.setName("Generals");
		list.add(team);
		
		return list;
	}

  ```
12. Guarde todo el trabajo. Detenga la aplicación si ya se está ejecutando e iníciela nuevamente. Abra [http://localhost:8080/teams](http://localhost:8080/teams). Deberías ver una respuesta JSON con los datos de tus equipos.

  **Parte 3 - Crear Spring Data JPA Repositories**
  13. Regrese a la clase de Team. Agregue las anotaciones JPA requeridas: la clase debe anotarse con @Entity, la identificación debe anotarse con @Id y @GeneratedValue.

14. Cree una nueva interfaz llamada "TeamRepository". Haga que extienda CrudRepository<Team,Long>.
  - ¡Asegúrate de crear esto como una interfaz, no como una clase!
  
15. Abra la clase de main/configuración de la aplicación (la que está anotada con @SpringBootApplication). Utilice @Autowired para inyectar la dependencia en una variable miembro de tipo TeamRepository. Nombra la variable como quieras (puedo sugerir: "teamRepository").

16. Agregue algo de lógica para completar inicialmente la base de datos: agregue un método public void init(). Anótelo con @PostConstruct. Corte y pegue el código de creación de equipo de su controlador en este método, excepto que, en lugar de devolver una Lista de equipos, llame al método saveAll() en el repositorio. Además, elimine los valores establecidos para los ID del equipo. Código de ejemplo:

 ```java
    public void init() {
		List<Team> list = new ArrayList<>();

		Team team = new Team();
		team.setLocation("Harlem");
		team.setName("Globetrotters");
		list.add(team);
		
		team = new Team();
		team.setLocation("Washington");
		team.setName("Generals");
		list.add(team);

		teamRepository.saveAll(list);
	}    
  ```
17. Regrese al TeamController. Utilice @Autowired para inyectar de dependencia una variable TeamRepository. Nombra la variable como quieras (puedo sugerir: "teamRepository").

18. Modifique la lógica en su método de controlador para simplemente devolver el resultado del método findAll() del repositorio:

 ```java
	@GetMapping("/teams")
	public Iterable<Team> getTeams() {
		return teamRepository.findAll();
	}
  ```
19. Agregue esta propiedad a application.properties para controlar cómo la implementación JPA (Hibernate) debe manejar el esquema de base de datos:
  ```java
        spring.jpa.hibernate.ddl-auto = update
  ```
20. Guarde todo el trabajo. Detenga la aplicación si ya se está ejecutando e iníciela nuevamente. Abra [http://localhost:8080/teams](http://localhost:8080/teams). Deberías ver una respuesta JSON con los datos de tus equipos.

  **Parte 4 (Optional)- Crear a simple Team endpoint**
  21. Regrese a TeamController y agregue un método que devuelva un único equipo con una identificación.
  - Nombra el método como quieras. Sugerencia: getTeam.
  - El tipo de devolución debe ser un Equipo.
  - Utilice una anotación @GetMapping para asignar este método al patrón "/teams/{id}".
  - Defina un parámetro llamado "id" de tipo Long anotado con @PathVariable.
  - Lógica: devuelve el resultado del método findById(id).get() del teamRepository. (FindById() devuelve un Java 8 "Opcional", y get() simplemente devuelve el equipo real.

22. Guarde todo el trabajo. Detenga la aplicación si ya se está ejecutando e iníciela nuevamente. Utilice [http://localhost:8080/teams](http://localhost:8080/teams) para anotar los valores de ID generados para cada equipo. Luego use URL como [http://localhost:8080/teams/1](http://localhost:8080/teams/1) o [http://localhost:8080/teams/2](http:// localhost:8080/teams/2) para obtener resultados para los equipos individuales.
