<!DOCTYPE html>

<%@ page language= "java" contentType= "text/html; charset=UTF-8" pageEncoding= "UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "spring" uri= "http://www.springframework.org/tags"%>
<%@ taglib prefix= "page" tagdir= "/WEB-INF/tags" %>
<%@ taglib prefix= "security" uri= "http://www.springframework.org/security/tags" %>

<html>
<head>
    <meta charset= "utf-8">
    <title>Login Page</title>
    <spring:url value= "/resources/css/bootstrap.css" var= "bootstrap" />
    <spring:url value= "/resources/css/signin.css" var= "signin" />
    <link href= "${bootstrap}" rel= "stylesheet" />
    <link href= "${signin}" rel= "stylesheet" />
    <base href="${pageContext.request.contextPath}/"/>
</head>
<body>
<form name= "form" action= "j_spring_security_check" method= "post" class= "form-signin">
    <security:authorize access= "hasAnyRole('ADMIN', 'USER')" var= "isUSer"/>
    <font size= "2" color= "red">
        <c:if test= "${not isUSer}">
            <c:if test= "${empty param.error}">
                Вы не вошли
            </c:if>
        </c:if>
    </font>

    <font size= "2" color= "green">
        <c:if test= "${isUSer}">Вы вошли как:
            <security:authentication property= "principal.username"/> с ролью:
            <b><security:authentication property= "principal.role"/></b>
        </c:if>
    </font>
    <br/>
    <c:if test= "${not empty param.error}">
        <font size= "2" color= "red"><b>Неправильный логин или пароль</b></font>
    </c:if>

    <h2 class= "form-signin-heading">Пожалуйста войдите</h2>

    <label for= "inputUsername" class= "sr-only"><spring:message code= "username" text= ""/></label>
    <input id= "inputUsername" class= "form-control" name= "j_username" required autofocus/>

    <label for= "inputPassword" class= "sr-only"><spring:message code= "pass" text= "Password"/></label>
    <input type= "password" id= "inputPassword" class= "form-control" name= "j_password" required/>

    <div class= "checkbox">
        <label>
            <input type= "checkbox" id= "rememberme"  name= "_spring_security_remember_me"/>Запомнить меня
        </label>
    </div>
    <input type= "submit" value= "Войти" class= "btn btn-lg btn-primary btn-block" >
    <br/>
    <a href= "javascript:history.back()">Назад</a>

    <br /><br />
    <p>Дефолтные админ и покупатель:</p>

    <b>ADMIN</b> <br />
    Login:<span style= "color: royalblue">Andrey</span> Password: <span style= "color: royalblue">12345</span> <br />
    <b>USER</b> <br />
    Login: <span style= "color: royalblue">Zlatan</span> Password: <span style= "color: royalblue">54321</span>
</form>
</body>

</html>
