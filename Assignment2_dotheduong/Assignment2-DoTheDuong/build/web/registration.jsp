<%-- 
    Document   : registration
    Created on : Jan 23, 2021, 2:14:41 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User Page JSP</title>
    </head>
    <body>
        
        <c:if test="${requestScope.USER_ERROR ==null}">
            <c:set var="requestScope.USER_ERROR.emailError" value=""/>
            <c:set var="requestScope.USER_ERROR.fullNameError" value=""/>
            <c:set var="requestScope.USER_ERROR.passwordError" value=""/>
            <c:set var="requestScope.USER_ERROR.confirmError" value=""/>
        </c:if>
        <a href="LogoutController">Login</a>
        <div>
            <h1>Register</h1>
            <form action="CreateUserController" method="POST" >
                Email<input type="email" name="txtEmail" >
                ${requestScope.USER_ERROR.emailError}</br>
                Full Name<input type="text" name="txtFullName">
                ${requestScope.USER_ERROR.fullNameError}</br>
                <input type="hidden" name="txtRoleID" value="US" >
                Password<input type="password" name="txtPassword">
                ${requestScope.USER_ERROR.passwordError}</br>
                Confirm<input type="password" name="txtConfirm">
                ${requestScope.USER_ERROR.confirmError}</br>
                <input type="submit" name="btnAction" value="Create User" >
            </form>
        </div>
    </body>
</html>
