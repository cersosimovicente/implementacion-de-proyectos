### Validación de datos utilizando JavaScript en el lado del cliente y Servlet en el lado del servidor
```html
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Validación</title>
    <script src="validacion.js"></script>
</head>
<body>
    <h2>Formulario de Validación</h2>
    <form action="ProcesarDatos" method="post" onsubmit="return validarFormulario()">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <input type="submit" value="Enviar">
    </form>
</body>
</html>
```
```javascript
function validarFormulario() {
    let nombre = document.getElementById("nombre").value;
    let email = document.getElementById("email").value;

    if (nombre === "" || email === "") {
        alert("Por favor, complete todos los campos.");
        return false;
    }

    // Puedes agregar más lógica de validación según tus necesidades, como validar el formato del correo electrónico.

    return true; // Envía el formulario si pasa la validación.
}

```
```java
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcesarDatos")
public class ProcesarDatosServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");

        // Aquí puedes realizar más procesamiento o almacenar los datos en una base de datos.

        response.getWriter().println("Datos recibidos: Nombre = " + nombre + ", Email = " + email);
    }
}

```
