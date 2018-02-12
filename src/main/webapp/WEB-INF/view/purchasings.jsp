<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<head>
    <script type="text/javascript" src="resources/js/admin.js" defer></script>
</head>

<page:template>
    <jsp:body>
        <header></header>
        <div class="container-fluid">
            <h2>Список совершенных покупок</h2>
            <table class="table table-striped display" id="datatablePur">
                <thead>
                <tr>
                    <th>Пользователь</th>
                    <th>Продукт</th>
                    <th>Дата</th>
                    <th>Цена</th>
                    <th>Количество</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </jsp:body>
</page:template>