# Tutorial Spring Security - Registration, Login, y Logout
# Desarrollo de funciones de registro, inicio de sesión y cierre de sesión con Spring Boot

En este tutorial, aprenderemos cómo desarrollar funciones de **registro**, **inicio de sesión** y **cierre de sesión** utilizando **Spring Boot**, **Spring Security**, **Spring Data JPA**, **Thymeleaf** y la base de datos **MySQL**.

## Objetivos
- Crear una aplicación web **Spring MVC** utilizando:
  - **Spring Boot** para la configuración del proyecto.
  - **Spring Security** para la gestión de autenticación y autorización.
  - **Spring Data JPA** para la interacción con la base de datos.
  - **Thymeleaf** como motor de plantillas para la vista.
  - **MySQL** como base de datos para almacenar la información de usuarios.

## Tecnologías utilizadas
1. **Spring Boot**: Para facilitar la configuración y puesta en marcha de la aplicación.
2. **Spring Security**: Para proporcionar autenticación y autorización.
3. **Spring Data JPA**: Para la interacción con la base de datos.
4. **Thymeleaf**: Para crear las vistas de la aplicación web.
5. **MySQL**: Base de datos relacional para almacenar información de usuarios y sesiones.

### ¿Qué aprenderás?
### 1. **Creación de una Aplicación Web Spring MVC con Spring Boot**
   - Aprenderás a crear una aplicación web basada en el patrón **Spring MVC** utilizando el marco **Spring Boot**, que simplifica la configuración y el desarrollo.

### 2. **Configuración de la Seguridad en el Proyecto con Spring Security**
   - Descubrirás cómo **configurar Spring Security** para proporcionar un sistema de autenticación y autorización robusto dentro de tu proyecto Spring Boot.

### 3. **Desarrollo de la Funcionalidad de Registro de Usuario**
   - Implementación completa de un sistema de **registro de usuarios**, desde el formulario de registro hasta la persistencia en la base de datos, garantizando la seguridad de los datos.

### 4. **Implementación de Funciones Personalizadas de Inicio de Sesión**
   - Configuración de un sistema de **inicio de sesión personalizado** que interactúa con la base de datos, validando usuarios y protegiendo rutas sensibles dentro de la aplicación.

### 5. **Autenticación con Spring Security utilizando UserDetailsService respaldado por una Base de Datos**
   - Implementaremos **UserDetailsService**, una clase que integra **Spring Security** con **JPA** para autenticar usuarios desde una base de datos MySQL.

### 6. **Funcionalidad de Cierre de Sesión en Spring Security**
   - Aprenderás a habilitar y personalizar la funcionalidad de **cierre de sesión**, que permitirá a los usuarios cerrar sesión de forma segura.

### 7. **Creación de Entidades JPA - Usuario y Rol (Relación Muchos a Muchos)**
   - Aprenderás a definir **entidades JPA** para modelar la relación entre usuarios y roles en una estructura de **muchos a muchos**, garantizando flexibilidad en la autorización.

### 8. **Configuración de la Base de Datos MySQL en el Proyecto Spring Boot**
   - Descubrirás cómo conectar **Spring Boot** con una base de datos **MySQL**, incluyendo la configuración de credenciales y la inicialización del esquema de base de datos.

### 9. **Desarrollo de Formularios de Registro e Inicio de Sesión con Thymeleaf**
   - Crearemos formularios de **registro e inicio de sesión** con **Thymeleaf**, asegurando que las interacciones entre el backend y el frontend sean fluidas.

### 10. **Integración de Spring Security en Thymeleaf**
   - Veremos cómo integrar **Spring Security** en vistas Thymeleaf, protegiendo componentes de la interfaz de usuario según los permisos del usuario autenticado.

### 11. **Uso de Atributos de Thymeleaf para Mostrar Información de Seguridad**
   - Aprenderás a usar los atributos de **Thymeleaf** para mostrar información relevante del usuario autenticado, como el **correo electrónico**, sus **roles** y otros **detalles de seguridad**.

## Flujo de aplicación

```mermaid
flowchart LR
    A[Browser] --> B[User Registration Controller]
    B --> C[User Service]
    C --> D[User Repository]
    D --> E[MySQL]
```

## Diseño de Base de Datos (Diagrama ER)
```mermaid
erDiagram
    USER {
        BIGINT id
        VARCHAR(255) email
        VARCHAR(255) first_name
        VARCHAR(255) last_name
        VARCHAR(255) password
    }
    
    ROLE {
        BIGINT id
        VARCHAR(255) name
    }
    
    USERS_ROLES {
        BIGINT user_id
        BIGINT role_id
    }
    
    USER ||--o{ USERS_ROLES : "has"
    ROLE ||--o{ USERS_ROLES : "has"

```
## Herramientas y tecnologías utilizadas




