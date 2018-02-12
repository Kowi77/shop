<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<head>
    <script type="text/javascript" src="resources/js/registration.js" defer></script>
</head>

<page:template>

    <jsp:attribute name="title">Zlatan1</jsp:attribute>

    <jsp:body>
        <header></header>
        <div class="col-lg-12">
            <h1 class="page-header">
                <spring:message code="content.registration"/>
            </h1>
        </div>
        <div>
            <form class="form-horizontal" id="registrationForm">
                <input type="hidden" id="id" name="id">
                <div class="form-group">
                    <label for="username" class="control-label col-xs-3">Имя</label>
                    <div class="col-xs-9">
                        <input type="text" class="form-control" id="username" name="username"
                               placeholder="Имя">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="control-label col-xs-3">Пароль</label>
                    <div class="col-xs-9">
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="(5-12) символов">
                    </div>
                </div>
                <input type="hidden" id="role" name="role" value="USER">
                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-9">
                        <button class="btn btn-primary" type="button" onclick="save()">
                            <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                        </button>
                    </div>
                </div>

            </form>
        </div>
        <div id="success" style="display: none" class="alert alert-success"></div>
        <div id="error" style="display: none" class="alert alert-danger"></div>
    </jsp:body>

</page:template>
