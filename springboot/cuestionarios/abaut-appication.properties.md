# Configurar la conexión a la base de datos

El archivo `application.properties` debajo de la carpeta de recursos contiene las propiedades que Spring Boot usará para configurar la aplicación. Agregar detalles de configuración de la base de datos, como la URL de la base de datos, el nombre de usuario y la contraseña.

## Configuración del archivo application.properties

```properties
# Url donde está el servicio de tu MySQL y el nombre de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/mydatabase  # acá va el nombre de tu db

# Usuario y contraseña para tu base de datos descrita en la línea anterior
spring.datasource.username=root
spring.datasource.password=

# [Opcional] Imprime en tu consola las instrucciones hechas en tu base de datos.
spring.jpa.show-sql = true

# Hibernate ddl auto (create, create-drop, update)
spring.jpa.hibernate.ddl-auto = create-drop

# El dialecto SQL hace que Hibernate genere un mejor SQL para la base de datos elegida
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect

```
## Detalles sobre spring.jpa.hibernate.ddl-auto
Los valores `create`, `create-drop`, `validate` y `update` básicamente influyen en cómo la administración de la herramienta de esquema manipulará el esquema de la base de datos en el inicio.

- **create:**  Crea el esquema y destruye los datos anteriores.
- **create-drop:** Crea su esquema, su caso de prueba agrega algunos datos simulados, ejecuta sus pruebas y luego, durante la limpieza del caso de prueba, los objetos del esquema se eliminan, dejando una base de datos vacía.
- **validate:**  Valida el esquema, no realiza cambios en la base de datos.
- **update:** Consulta la API del controlador JDBC para obtener los metadatos de la base de datos y luego Hibernate compara el modelo de objetos que crea basándose en la lectura de sus clases anotadas e intentará ajustar el esquema al inicio de la aplicación. Intentará agregar nuevas columnas, restricciones, etc., pero nunca eliminará una columna o restricción que puede haber existido anteriormente pero que ya no forma parte del modelo de objetos de una ejecución anterior.
## Recomendaciones
- En los escenarios de casos de prueba, es probable que use create-drop.
- En el desarrollo, es común ver a los desarrolladores usar update.
- En producción, es muy recomendable que use none o simplemente no especifique esta propiedad. Es una práctica común que los DBA revisen los scripts de migración en busca de cambios en la base de datos, particularmente si su base de datos se comparte entre múltiples servicios y aplicaciones.
