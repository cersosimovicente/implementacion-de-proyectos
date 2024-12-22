# Aplicacion para gestion de productos
## Objetivo
Crear un CRUD bajo el modelo MVC que permita cambiar la fuente de datos sin modificar el código.
Se requiere implementar el patrón Strategy para la fuente de datos. Java, Maven, y NIO.2 para el sistema de gestión de productos:

## Estructura del Proyecto
```plaintext
src/
├── main/
│   ├── java/
│   │   ├── controller/
│   │   │   └── ProductController.java
│   │   ├── model/
│   │   │   ├── Product.java
│   │   │   └── DataSourceStrategy.java
│   │   ├── repository/
│   │   │   ├── FileDataSource.java
│   │   │   ├── InMemoryDataSource.java
│   │   │   └── DatabaseDataSource.java
│   │   └── view/
│   │       └── ProductView.java
│   └── resources/
│       └── application.properties
├── pom.xml

```

