<%-- 
    Document   : history
    Created on : Jan 30, 2021, 3:43:17 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page JSP</title>
    </head>
    <body>

        <a href="QuizPageController" class="quiz">Roll back</a> 
        <a href="LogoutController" class="logout">Logout</a>
        <h1>WelCome : ${sessionScope.LOGIN_USER.fullName}</h1>
        <h2>History : </h2>
        <form action="SearchHitoryController" method="POST">
            Subject Name:  
            <select name="cmbSubject" >
                <c:set var="all" value=""/>
                <c:if test="${param.cmbSubject==null}">
                    <c:forEach var="subject" varStatus="counter" items="${sessionScope.LIST_ALL_SUBJECT}">
                        <option value="${subject.subjectID}">${subject.subjectName}</option>                                   
                    </c:forEach>                     
                        <option value="">All</option>
                </c:if>
                <c:if test="${param.cmbSubject==all}">     
                    <c:forEach var="subject" varStatus="counter" items="${sessionScope.LIST_ALL_SUBJECT}">
                        <option value="${subject.subjectID}">${subject.subjectName}</option>                                   
                    </c:forEach>                     
                        <option value="" selected="true">All</option>
                </c:if>    
                <c:if test="${param.cmbSubject!=null && param.cmbSubject!=all}">
                    <c:forEach var="subject" varStatus="counter" items="${sessionScope.LIST_ALL_SUBJECT}">
                        <c:if test="${param.cmbSubject==subject.subjectID}">
                            <option value="${subject.subjectID}" selected="true">${subject.subjectName}</option>   
                        </c:if>                                   
                        <c:if test="${param.cmbSubject!=subject.subjectID}">
                            <option value="${subject.subjectID}">${subject.subjectName}</option>   
                        </c:if> 
                    </c:forEach>
                    <option value="">All</option>
                </c:if> 
            </select>
            <input type="submit" name="btnAction" value="Search History" />
        </form>



        <c:if test="${requestScope.MESSAGE !=null}">
            <h3>${requestScope.MESSAGE}</h3>
        </c:if>

        <c:if test="${requestScope.LIST_HISTORY !=null}">
            <c:if test="${not empty requestScope.LIST_HISTORY}">

                <table border="1">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>SubjectID</th>
                            <th>Mark</th>
                            <th>Answers correct</th>
                            <th>Submit Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="string" varStatus="counter" items="${requestScope.LIST_HISTORY}">
                            <c:set var="arr" value="${f:split(string, '_')}" /> 
                            <c:forEach var="subject" varStatus="counter1" items="${sessionScope.LIST_ALL_SUBJECT}">
                                <c:if test="${subject.subjectID ==arr[1]}">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${arr[1]}</td>
                                        <td>${arr[2]}</td>
                                        <td>${(arr[2]*subject.numberQuestion)/10}/${subject.numberQuestion}</td>       
                                        <td>${arr[3]}</td>  
                                    </tr>
                                </c:if>
                            </c:forEach>

                        </c:forEach>
                    </tbody>
                </table>                           

            </c:if>
        </c:if>
            
            <footer >
                <div class="footer">
                    <ul class="pagination justify-content-center">
                        <c:if test="${requestScope.TOTAL_PAGE!=null && requestScope.TOTAL_PAGE>1}">
                            <c:forEach var="count" begin="1" end="${requestScope.TOTAL_PAGE}">
                                <li class="page-item">
                                    <a class="page-link" href="SearchHitoryController?txtCurrentPage=${count}&cmbSubject=${param.cmbSubject}">${count}</a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>   
            </footer>
    </body>
</html>
