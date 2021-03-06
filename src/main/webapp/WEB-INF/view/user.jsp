<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<head>
    <script type="text/javascript" src="resources/js/user.js" defer></script>
    <base href="${pageContext.request.contextPath}/"/>
    <title>Internet-shop</title>
</head>

<page:template>

    <jsp:body>
        <header></header>
        <div class="container-fluid">
            <h2>Список доступных товаров</h2>
            <table class="table table-striped display" id="datatable">
                <thead>
                <tr>
                    <th>Наименование</th>
                    <th>Описание</th>
                    <th>Цена</th>
                    <th>Количество</th>
                    <th>Купить</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <%--Form for choose good's quantity good--%>
        <div class="modal fade" id="purchasing">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h2 class="modal-title" id="modalTitle"></h2>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" id="basket">
                            <input type="hidden" id="id" name="id">

                            <div class="form-group">
                                <label for="name" class="control-label col-xs-3">Название</label>
                                <div class="col-xs-9">
                                    <input type="text" class="form-control" id="name" name="name" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="description" class="control-label col-xs-3">Описание</label>
                                <div class="col-xs-9">
                                    <input type="text" class="form-control" id="description" name="description" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="price" class="control-label col-xs-3">Цена</label>
                                <div class="col-xs-9">
                                    <input type="number" step="0.01" min="0" class="form-control" id="price" name="price" readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="quantity" class="control-label col-xs-3">Количество</label>
                                <div class="col-xs-9">
                                    <input type="number" min="1" class="form-control" id="quantity" name="quantity"
                                           placeholder="0">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-offset-3 col-xs-9">
                                    <button class="btn btn-primary" type="button" onclick="purchase('${pageContext.request.userPrincipal.name}')">
                                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div id="success" style="display: none" class="alert alert-success"></div>
        <div id="error" style="display: none" class="alert alert-danger"></div>

    </jsp:body>

</page:template>
