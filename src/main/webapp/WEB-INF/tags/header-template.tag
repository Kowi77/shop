<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-left">
                <a href="/index.html"><spring:message code="navMenu.back"/></a>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <security:authorize access="hasAnyRole('ADMIN', 'USER')" var="isUSer"/>
                <c:if test="${not isUSer}">
                    <li style="padding-top: 15px; padding-bottom: 15px; color: red">
                        <c:if test="${empty param.error}">
                            <spring:message code="navMenu.notLogin"/>
                        </c:if>
                    </li>
                    <li> <a style="color: Green;" href="<c:url value="/login.html"/>"><spring:message code="navMenu.login"/></a> </li>
                </c:if>
                <c:if test="${isUSer}">
                    <li style="padding-top: 15px; padding-bottom: 15px; color: green">
                        <spring:message code="navMenu.existLogin"/>
                        <security:authentication property="principal.username"/>
                    </li>
                    <li> <a style="color: red;" href="<c:url value="/j_spring_security_logout"/>"><spring:message code="navMenu.logout"/></a> </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
