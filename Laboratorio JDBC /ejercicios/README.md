# Ejercicios JDBC

Script de Base de Datos MySQL para JDBC



### Ejercicio 1: Conexión a la base de datos
Crea una aplicación Java que se conecte a una base de datos MySQL llamada empresa. Verifica la conexión y muestra un mensaje por consola indicando si la conexión fue exitosa o no.

### Ejercicio 2: Insertar registros
Escribe un programa en Java que inserte un nuevo registro en la tabla empleados con los campos: id, nombre, apellido, email, salario.

### Ejercicio 3: Leer registros
Desarrolla un programa que consulte todos los registros de la tabla productos y los muestre en consola.
La tabla tiene los campos: id, nombre, precio, stock.

### Ejercicio 4: Búsqueda por parámetro
Modifica el ejercicio anterior para que el usuario pueda buscar productos por nombre (o parte del nombre) desde la consola.

### Ejercicio 5: Actualizar registros
Crea una función que permita actualizar el salario de un empleado, recibiendo como parámetros su id y el nuevo salario.

### Ejercicio 6: Eliminar registros
Implementa un método que elimine un registro de la tabla empleados recibiendo el id como parámetro.

### Ejercicio 7: Uso de PreparedStatement
Reescribe los ejercicios 2, 4 y 5 utilizando PreparedStatement en lugar de Statement, para evitar inyecciones SQL.

### Ejercicio 8: Transacciones
Simula una transferencia bancaria entre dos cuentas de la tabla cuentas (id, titular, saldo).
Crea una transacción que:
1. Reste el monto de una cuenta origen.
2. Sume el mismo monto a la cuenta destino.
3. Si ocurre un error en cualquier paso, revierte la transacción.

### Ejercicio 9: Patrón DAO
Implementa una clase DAO (ProductoDAO) que tenga métodos para insertar, actualizar, eliminar y listar productos. Utiliza buenas prácticas como separación de lógica, manejo de conexiones, y reutilización de código.

###  Ejercicio 10: Interfaz de usuario simple
Crea una interfaz por consola para interactuar con una tabla clientes y permitir al usuario:
- Listar todos los clientes.
- Buscar un cliente por ID.
- Insertar un nuevo cliente.
- Actualizar su información.
- Eliminarlo.

