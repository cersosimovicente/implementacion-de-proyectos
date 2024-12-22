# Proyecto: Gestión de Fuentes de Datos con el Patrón Strategy
## Descripción General
En este proyecto, los estudiantes implementarán una aplicación en Java que permita gestionar diferentes fuentes de datos utilizando el patrón de diseño Strategy. La aplicación estará diseñada para ser flexible, de manera que pueda cambiar dinámicamente la fuente de datos sin necesidad de modificar el código principal.

El proyecto simula un sistema de almacenamiento de datos que puede trabajar con:

1. Archivos locales (`FileDataSource`).
2. Memoria (`InMemoryDataSource`).
3. Base de datos MySQL (`MysqlDataSource`).

## Objetivo del Proyecto
El objetivo es aprender a implementar el patrón Strategy y aplicar buenas prácticas de diseño de software, como el principio de Open/Closed y la separación de responsabilidades. Además, se busca introducir a los estudiantes al uso de MysqlDataSource para conectarse a una base de datos MySQL.

## Requerimientos Técnicos
1. Patrón Strategy:
   - Diseñar una interfaz común `DataSourceStrategy` que defina los métodos básicos de interacción con las fuentes de datos.
   - Crear tres implementaciones concretas:
     - `FileDataSource`: Maneja datos en un archivo local.
     - `InMemoryDataSource`: Maneja datos en memoria.
     - `MysqlDataSource`: Conecta y gestiona datos en una base de datos MySQL.
2. Funcionalidades:
   - Conectarse a la fuente de datos seleccionada.
   - Insertar, consultar y eliminar registros de manera uniforme, independientemente de la fuente de datos utilizada.
   - Cambiar la fuente de datos de manera dinámica durante la ejecución.
3. Estructura del Proyecto:
   - Utilizar Maven para gestionar dependencias.
   - Organizar el código en paquetes siguiendo una arquitectura clara y modular:
```css
├── src/main/java
    ├── strategy
    │   ├── DataSourceStrategy.java
    │   ├── FileDataSource.java
    │   ├── InMemoryDataSource.java
    │   └── MysqlDataSource.java
    ├── context
    │   └── DataSourceContext.java
    └── main
        └── Main.java
```
