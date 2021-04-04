<%-- 
    Document   : result
    Created on : Feb 7, 2021, 12:53:25 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>RESULT</h1>
        <h1>Welcome ${sessionScope.LOGIN_USER.fullName}</h1>  
        <table border="1">
            <thead>
                <tr>
                    <th>Total statement Correct</th>
                    <th>Total Score</th>
                    <th>Date submit</th>
                    <th>Subject</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${requestScope.ANSWER_CORRECT}</td>
                    <td>${requestScope.SCORE}</td>
                    <td>${requestScope.DATE_SUBMIT}</td>
                    <td>${requestScope.SUBJECT}</td>
                </tr>
            </tbody>
        </table>
        <a href="QuizPageController" >Take Quiz</a>
    </body>
</html>
