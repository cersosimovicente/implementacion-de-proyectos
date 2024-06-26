### Clase LoginBean
```java
package com.login.bean;
 
public class LoginBean {
 
    private String userName;
    private String password;
    .
    .
    //sets & gets...
    .
    .
}
```
### Clase LoginServlet
```java
package com.login.controller;
.
.
.
import com.login.bean.LoginBean;
import com.login.dao.LoginDao;
 
public class LoginServlet extends HttpServlet {
 

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
    //recuperar los datos del formulario
    String userName = .......;
    String password = .......;

    //crear un objejeto loginbean para encapsular los datos que vienen del formulario
    LoginBean loginBean = .......;

    //setear el objeto con las variables userName y password
    loginBean.setUserName(....);
    loginBean.setPassword(....);
    //Crear un objeto loginDao para acceder a los metodos del dao
    LoginDao loginDao = .....;
 
    try
    {
        String userValidate = loginDao.authenticateUser(loginBean);
 
        if(userValidate.equals("Admin_Role"))
        {
            System.out.println("Admin's Home");
 
            HttpSession session = request.getSession(); //Creating a session
            session.setAttribute("Admin", userName); //setting session attribute
            request.setAttribute("userName", userName);
 
            request.getRequestDispatcher("/JSP/Admin.jsp").forward(request, response);
        }
        else if(userValidate.equals("Editor_Role"))
        {
            System.out.println("Editor's Home");
 
            HttpSession session = request.getSession();
            session.setAttribute("Editor", userName);
            request.setAttribute("userName", userName);
 
            request.getRequestDispatcher("/JSP/Editor.jsp").forward(request, response);
        }
        else if(userValidate.equals("User_Role"))
        {
            System.out.println("User's Home");
 
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(10*60);
            session.setAttribute("User", userName);
            request.setAttribute("userName", userName);
 
            request.getRequestDispatcher("/JSP/User.jsp").forward(request, response);
        }
        else
        {
            System.out.println("Error message = "+userValidate);
            request.setAttribute("errMessage", userValidate);
 
            request.getRequestDispatcher("/JSP/Login.jsp").forward(request, response);
        }
    }
    catch (IOException e1)
    {
        e1.printStackTrace();
    }
    catch (Exception e2)
    {
        e2.printStackTrace();
    }
} //End of doPost()
}
```
