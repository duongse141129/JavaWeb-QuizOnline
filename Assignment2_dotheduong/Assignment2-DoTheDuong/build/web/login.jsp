<%-- 
    Document   : login
    Created on : Oct 17, 2020, 3:55:38 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">   
    </head>
    <body>       

            <h1>Login </h1>
            <form action="LoginController" method="POST" >
                Email:  <input type="email" name="txtEmail" /></br>
                Password: <input type="password" name="txtPassword" /></br>
                <input type="submit" name="btnAction" value="Login" />
            </form>
            <c:if test="${sessionScope.LOGIN_INVALID !=null && not empty sessionScope.LOGIN_INVALID}">
                <h5>${sessionScope.LOGIN_INVALID}</h5>
            </c:if>
            <a href="RegistrationController" class="create">Register</a>  
    </body>
</html>
