<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<head>
    <base href="${pageContext.request.contextPath}/"/>
</head>

<page:template>
    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Ошибка
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Домой</a>
                        </li>
                        <li><a href="javascript:history.back()"/>Назад</a>
                        </li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <p>Причина</p>
                    <b>${exceptionMsg}</b>
                </div>
            </div>
            <hr>
        </div>
    </jsp:body>
</page:template>
