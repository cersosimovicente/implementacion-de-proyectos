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
## 1. Crear proyecto de Spring Boot 
### Etapa 1:
Crear un proyecto de Spring Boot con Spring Initializer

## 2. Estructura del proyecto

```mermaid
graph LR
    A[Sistema] --> B[controller]
    A --> C[model]
    A --> D[repository]
    A --> E[service]
```
## 3. Dependencias de Maven
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.3.4</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.cersocode</groupId>
	<artifactId>employee-management-webapp</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>employee-management-webapp</name>
	<description>Demo project for Spring Boot</description>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>21</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<!--<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
<!--		<dependency>-->
<!--			<groupId>org.thymeleaf.extras</groupId>-->
<!--			<artifactId>thymeleaf-extras-springsecurity6</artifactId>-->
<!--		</dependency>-->

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>
```
## 4. Configuración de la base de datos MySQL o H2
```yaml
spring:
  application:
    name: employee-management-webapp
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: password
  h2:
    console:
      enabled: true
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
```
## 5. Capa de modelo - Crear entidades JPA
```java
package com.cersocode.employee_management_webapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;
}
```
## 6. Capa de Repositorio
```java
package com.cersocode.employee_management_webapp.repository;

import com.cersocode.employee_management_webapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
}
```
## 7. DTO - Registro de UsuarioDto 
### se implementa en la etapa 2
## 8. Capa de servicio
```java
package com.cersocode.employee_management_webapp.service;

import com.cersocode.employee_management_webapp.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();
    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    void deleteEmployeeById(Long id);
}
```
```java
package com.cersocode.employee_management_webapp.service;

import com.cersocode.employee_management_webapp.model.Employee;
import com.cersocode.employee_management_webapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return employee;
    }
    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
```
## 9. Configuración de seguridad de Spring
### se implementa en la etapa 2
## 10. Plantillas thymeleaf
`index.html`
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee Management System</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
</head>
<body>
    <div class="container my-2">
        <h1>Employees List</h1>
        <a th:href = "@{/showNewEmployeeForm}" class="btn btn-primary btn-sm mb-3"> Add Employee </a>

        <table class = "table table-striped table-responsive-md">
            <thead>
                <tr>
                    <th>Employee First Name</th>
                    <th>Employee Last Name</th>
                    <th>Employee Email</th>
                </tr>
            </thead>
            <tbody>
                <tr th:each="employee : ${listEmployees}">
                    <td th:text="${employee.firstName}"></td>
                    <td th:text="${employee.lastName}"></td>
                    <td th:text="${employee.email}"></td>
                    <td> <a th:href="@{/showFormForUpdate/{id}(id=${employee.id})}" class="btn btn-primary">Update</a>
                        <a th:href="@{/deleteEmployee/{id}(id=${employee.id})}" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
```
`new_employee.html`
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee Management System</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
</head>
<body>
    <div class="container my-2">
        <h1>Employees List</h1>
        <a th:href = "@{/showNewEmployeeForm}" class="btn btn-primary btn-sm mb-3"> Add Employee </a>

        <table class = "table table-striped table-responsive-md">
            <thead>
                <tr>
                    <th>Employee First Name</th>
                    <th>Employee Last Name</th>
                    <th>Employee Email</th>
                </tr>
            </thead>
            <tbody>
                <tr th:each="employee : ${listEmployees}">
                    <td th:text="${employee.firstName}"></td>
                    <td th:text="${employee.lastName}"></td>
                    <td th:text="${employee.email}"></td>
                    <td> <a th:href="@{/showFormForUpdate/{id}(id=${employee.id})}" class="btn btn-primary">Update</a>
                        <a th:href="@{/deleteEmployee/{id}(id=${employee.id})}" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
```
`update_ployee.html`
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee Management System</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Employee Management System</h1>
    <hr>
    <h2>Update Employee</h2>

    <form action="#" th:action="@{/saveEmployee}" th:object="${employee}"
          method="POST">

        <!-- Add hidden form field to handle update -->
        <input type="hidden" th:field="*{id}" />

        <input type="text" th:field="*{firstName}" class="form-control mb-4 col-4">

        <input type="text" th:field="*{lastName}" class="form-control mb-4 col-4">

        <input type="text" th:field="*{email}" class="form-control mb-4 col-4">

        <button type="submit" class="btn btn-info col-2"> Update Employee</button>
    </form>

    <hr>

    <a th:href = "@{/}"> Back to Employee List</a>
</div>
</body>
</html>
```
![Texto alternativo](imgs/employee_list.png)
![Texto alternativo](imgs/employee_add.png)
![Texto alternativo](imgs/employee_update.png)

