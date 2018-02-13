<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>


<head>
  <base href="${pageContext.request.contextPath}/"/>
</head>

<page:template>

  <jsp:attribute name="title"></jsp:attribute>

  <jsp:body>

    <header></header>
    <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">
          <spring:message code="navMenu.home"/>
        </h1>
      </div>
      <div class="col-md-4">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4><i class="fa fa-fw fa-check"></i>Если Вы администратор</h4>
          </div>
          <div class="panel-body">
            <a href="/admin" class="btn btn-default">Я админ</a>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4><i class="fa fa-fw fa-gift"></i>Если Вы наш покупатель</h4>
          </div>
          <div class="panel-body">
            <a href="/user" class="btn btn-default">Я покупатель</a>
          </div>
        </div>
      </div>
      <div class="col-md-4">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h4><i class="fa fa-fw fa-gift"></i>Если Вы еще не наш покупатель</h4>
          </div>
          <div class="panel-body">
            <a href="/registration" class="btn btn-default">Регистрация</a>
          </div>
        </div>
      </div>
    </div>
  </jsp:body>
</page:template>
