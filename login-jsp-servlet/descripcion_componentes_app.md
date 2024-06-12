# Cómo desarrollar una aplicación de inicio de sesión basada en roles en Java

Este ejemplo de inicio de sesión Java basado en roles contiene JSP, servlets Java, objetos de sesión y un servidor de base de datos MySQL.

## ¿Qué quiere decir con inicio de sesión basado en roles?
Cuando desee segregar el nivel de acceso para cada usuario según sus roles, como administrador, profesor, estudiante, etc., en su aplicación querrá asignar un rol específico a cada usuario que inicia sesión para que sea más fácil administrar aplicaciones grandes. En este ejemplo verá 3 roles: administrador, editor y usuario.

## ¿Qué es una sesión?
HTTP es un protocolo sin estado, lo que significa que la conexión entre el servidor y el navegador se pierde una vez finalizada la transacción. Realmente no se puede rastrear quién realizó una solicitud y cuándo se canceló. La sesión nos ayuda a mantener un estado entre el cliente y el servidor y puede constar de múltiples solicitudes y respuestas entre el cliente y el servidor. Dado que tanto HTTP como el servidor web no tienen estado, se utilizaría información única (ID de sesión) para crear una sesión y este ID de sesión se pasa entre el servidor y el cliente en cada solicitud.

### Login.jsp
El JSP contiene un formulario HTML simple para ingresar las credenciales de inicio de sesión. Para poder iniciar sesión en cualquier aplicación.

### LoginServlet.java
El servlet es un controlador en el patrón MVC. Actúa como un puente entre la Vista y el Modelo, es decir, recibe las solicitudes de la UI y las envía al modelo (lógica de negocios) y luego a la operación relacionada.

### LoginBean.java
Los JavaBeans son clases que encapsulan muchos objetos en un solo objeto. El objeto único facilita el acceso a todos los miembros de la clase bean.

### LoginDao.java
Esta clase es parte del objeto de acceso a datos. El objeto de acceso a datos (DAO) se utiliza para abstraer y encapsular todo el acceso a la fuente de datos. El DAO es básicamente un objeto o una interfaz que proporciona acceso a una base de datos subyacente o cualquier otro almacenamiento persistente. En esta clase validaremos el nombre de usuario y la contraseña ingresados por el usuario con el nombre de usuario y la contraseña almacenados en la base de datos durante el proceso de registro. Según el rol del usuario se asigna el tipo de rol apropiado.

### ConexiónDB.java
Estamos utilizando la base de datos MySQL en esta aplicación. Podemos utilizar cualquier servidor de base de datos que admita Java. Se deben utilizar las URL de conexión y de controlador adecuadas según la base de datos elegida.

**Nota:** No olvide agregar el jar dependiente para el servidor de la base de datos. En nuestro caso es `mysql-connector-java.jar`. La última versión 8 de MySQL necesita algunos ajustes y funciona perfectamente hasta la versión 8.

### Administrador.jsp
### Usuario.jsp
### LogoutServlet.java.java

### web.xml
El `web.xml` se conoce como descriptor de implementación. Enumera todos los servlets utilizados en la aplicación. Recuerde dar un nombre de clase completo en la clase de servlet. Presenta algunas configuraciones adicionales como un nombre de archivo de bienvenida que conduce al nombre del archivo mencionado cuando se carga esta aplicación. Además, el parámetro `session-timeout` define que la sesión estará activa durante 10 minutos.

