### Enunciado para el Código de Proyecto de Inicio de Sesión

Este proyecto implementa un sistema básico de autenticación para usuarios en una aplicación web utilizando Java EE. El sistema distingue entre tres tipos de roles de usuario: Administrador, Editor y Usuario, redirigiéndolos a diferentes páginas de inicio según su rol tras la autenticación.

#### Componentes del Proyecto

1. **LoginBean (com.login.bean.LoginBean)**
    - Una clase Java que actúa como un bean para encapsular los datos de inicio de sesión del usuario (nombre de usuario y contraseña).
    - Métodos:
        - `getUserName()`, `setUserName(String userName)`: para obtener y establecer el nombre de usuario.
        - `getPassword()`, `setPassword(String password)`: para obtener y establecer la contraseña.

2. **LoginServlet (com.login.controller.LoginServlet)**
    - Un servlet que maneja las solicitudes de inicio de sesión.
    - Método `doPost(HttpServletRequest request, HttpServletResponse response)`:
        - Recibe los parámetros de nombre de usuario y contraseña del formulario de inicio de sesión.
        - Crea una instancia de `LoginBean` y establece los valores de usuario y contraseña.
        - Llama al método `authenticateUser` de `LoginDao` para verificar las credenciales del usuario.
        - Según el rol devuelto por `LoginDao`, redirige al usuario a la página correspondiente (`Admin.jsp`, `Editor.jsp`, `User.jsp`) o muestra un mensaje de error en caso de credenciales inválidas.

3. **LogoutServlet (com.login.controller.LogoutServlet)**
    - Un servlet que maneja las solicitudes de cierre de sesión.
    - Método `doGet(HttpServletRequest request, HttpServletResponse response)`:
        - Invalida la sesión actual y redirige al usuario a la página de inicio de sesión con un mensaje de confirmación de cierre de sesión.

4. **LoginDao (com.login.dao.LoginDao)**
    - Clase que maneja la lógica de autenticación del usuario.
    - Método `authenticateUser(LoginBean loginBean)`:
        - Establece una conexión con la base de datos y verifica si las credenciales del usuario coinciden con las almacenadas en la base de datos.
        - Retorna el rol del usuario (Admin, Editor, User) si las credenciales son válidas, o un mensaje de error si no lo son.

5. **DBConnection (com.login.util.DBConnection)**
    - Clase utilitaria para gestionar la conexión con la base de datos.
    - Método `createConnection()`:
        - Establece y retorna una conexión a la base de datos MySQL.

6. **Páginas JSP**
    - **Login.jsp**: Página de inicio de sesión donde los usuarios ingresan sus credenciales.
    - **Admin.jsp**: Página de inicio para el rol de Administrador.
    - **Editor.jsp**: Página de inicio para el rol de Editor.
    - **User.jsp**: Página de inicio para el rol de Usuario.

#### Descripción del Flujo

1. El usuario accede a `Login.jsp` y envía su nombre de usuario y contraseña.
2. `LoginServlet` procesa la solicitud, utilizando `LoginDao` para autenticar al usuario.
3. Dependiendo del rol del usuario, `LoginServlet` redirige a la página correspondiente.
4. Si el usuario desea cerrar sesión, `LogoutServlet` invalida la sesión y redirige a `Login.jsp`.

Este sistema proporciona una estructura básica pero completa para la autenticación de usuarios basada en roles, utilizando servlets, beans y páginas JSP en una aplicación web Java EE.

