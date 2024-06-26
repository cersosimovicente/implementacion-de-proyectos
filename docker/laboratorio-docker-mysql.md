# **Trabajo Práctico: Configuración de una Imagen Docker de MySQL**

## **Objetivo**
El objetivo de este trabajo práctico es que los alumnos aprendan a configurar y desplegar una base de datos MySQL utilizando Docker. Además, se espera que sean capaces de conectarse a la base de datos, ejecutar consultas básicas y demostrar el uso de persistencia de datos con volúmenes.

## **Requisitos Previos**
- Conocimientos básicos de MySQL.
- Conocimientos básicos de Docker.
- Docker instalado en la máquina local.
- (Opcional) Docker Compose instalado.

## **Instrucciones**

### **Parte 1: Configuración de Docker**

1. **Instalar Docker:**
   Si aún no lo has hecho, instala Docker siguiendo las instrucciones oficiales para tu sistema operativo:
   - [Instrucciones de instalación de Docker para Windows](https://docs.docker.com/desktop/install/windows-install/)
   - [Instrucciones de instalación de Docker para macOS](https://docs.docker.com/desktop/install/mac-install/)
   - [Instrucciones de instalación de Docker para Linux](https://docs.docker.com/engine/install/)

2. **Verificar la instalación:**
   Abre una terminal y ejecuta el siguiente comando para verificar que Docker se ha instalado correctamente:
   ```sh
   docker --version
   ```
### ***Parte 2: Configuración de MySQL con Docker**
1. Descargar la imagen de MySQL:
- Ejecuta el siguiente comando en la terminal para descargar la imagen de MySQL desde Docker Hub:
```sh
docker pull mysql:latest
```
2. Crear un contenedor MySQL:
- Crea un contenedor MySQL utilizando el siguiente comando, reemplazando my-secret-pw con una contraseña segura para el usuario root:
```sh
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:latest
```
- --name mysql-container: asigna un nombre al contenedor.
- -e MYSQL_ROOT_PASSWORD=my-secret-pw: establece la contraseña del usuario root.
- -d: ejecuta el contenedor en segundo plano.
3. Verificar el contenedor:
- Asegúrate de que el contenedor está funcionando ejecutando:
```sh
docker ps
```
### **Parte 3: Conexión a MySQL**
1. Conectarse al contenedor:
- Para acceder a la línea de comandos de MySQL dentro del contenedor, ejecuta:
```sh
docker exec -it mysql-container mysql -u root -p
```
Introduci la contraseña que especificaste (my-secret-pw).
```sql
CREATE DATABASE testdb;
```
3. Usar la base de datos:
- Cambia a la base de datos testdb:
```sql
USE testdb;
```
4. Crear una tabla:
- Crea una tabla simple llamada users:
```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);
```
5. Insertar datos:
- Inserta algunos datos en la tabla:
```sql
INSERT INTO users (name, email) VALUES ('Alice', 'alice@example.com'), ('Bob', 'bob@example.com');
```
6. Consultar datos:
- Realiza una consulta para verificar que los datos se han insertado correctamente:
```sql
SELECT * FROM users;
```
### **Parte 4: Persistencia de Datos**
1. Detener el contenedor:
- Detener el contenedor MySQL:
```sql
docker stop mysql-container
```
2. Eliminar el contenedor:
- Elimina el contenedor MySQL:
```sql
docker rm mysql-container
```
3. Crear un volumen:
- Crea un volumen para persistir los datos:
```sql
docker volume create mysql-volume
```
4. Correr el contenedor con el volumen:
- Vuelve a crear el contenedor MySQL, esta vez montando el volumen:
```sql
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=my-secret-pw -v mysql-volume:/var/lib/mysql -d mysql:latest
```
5. Verificar la persistencia:
- Conectate nuevamente al contenedor y verifica que la base de datos y los datos en la tabla siguen allí:
```sql
docker exec -it mysql-container mysql -u root -p
USE testdb;
SELECT * FROM users;
```
# Material Entregable

## Cada alumno debe entregar:

1. **Un informe en formato PDF que contenga:**
   - Descripción del proceso seguido para configurar y conectar a MySQL usando Docker.
   - Capturas de pantalla de cada paso clave, incluyendo:
     - El contenedor en ejecución (`docker ps`).
     - La conexión a MySQL.
     - La creación de la base de datos y la tabla.
     - La inserción y consulta de datos.
     - La configuración y verificación del volumen.
   - Reflexión sobre la experiencia y cualquier desafío encontrado.

2. **Un archivo SQL** con los comandos utilizados para crear la base de datos, la tabla y los datos insertados.

## Criterios de Evaluación

- **Precisión:** La base de datos y el contenedor deben estar configurados correctamente y funcionar como se describe.
- **Claridad:** El informe debe ser claro y fácil de seguir, con explicaciones detalladas y capturas de pantalla.
- **Compleción:** Se deben cubrir todos los pasos del trabajo práctico, y el archivo SQL debe estar completo y correcto.

## Fecha de Entrega

- **Fecha de entrega:** 
