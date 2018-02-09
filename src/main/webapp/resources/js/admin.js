var ajaxGoods = "goods/";
var ajaxGood = "good/";
var datatableApi;
var form=$('#detailsForm');

$.ajaxSetup({
    converters: {
        "text json": function (stringData) {
            var json = JSON.parse(stringData);
            $(json).each(function () {
                this.date = this.date.replace('T', ' ').substr(0, 16);
                console.log(this.employer);
                n = this.employer.split(' ');
                this.employer = n[0] + " " + n[1].substr(0,1) + ". " + n[2].substr(0, 1) + ".";
                console.log(this.employer);
            });
            return json;
        }
    }
});

function updateTable() {
    $.ajax({
        type: "GET",
        url: ajaxGoods,
        dataType: 'json',
        success: function (data) {
            datatableApi.clear().rows.add(data).draw();
        }
    });
}

function updateRow(id) {
    $("#modalTitle").html("Редактирование продукта");
    $.get(ajaxGood + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
}

function add() {
    $("#modalTitle").html("Добавление продукта");
    $("#detailsForm").find(":input").val("");
    $("#editRow").modal();
}

function deleteRow(id) {
    $.ajax({
        url: ajaxGood + id,
        type: "DELETE",
        success: function () {
            updateTable();
            successNoty("Продукт удален");
        }
    });
}

function save() {
    $.ajax({
        type: "POST",
        url: "good/save",
        data:form.serialize(),
        success: function () {
            $("#editRow").modal("hide");
            updateTable();
            successNoty("Продукт сохранен");
        }
    });
}

//Отрисовка таблицы отфильтрованными данными
function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

//Datatable
$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxGoods,
            "dataSrc": ""
        },
        "paging": true,
        "info": false,
        "columns": [
            {"data": "name"},
            {"data": "description"},
            {"data": "price"},
            {"data": "quantity"},
            {"orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {"orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [[0,"asc"]],
        "initComplete": errorHandling
    });
});

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");'>" +
            "<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    return "<a onclick='deleteRow(" + row.id + ");'>" +
        "<span class='glyphicon glyphicon-remove' aria-hidden='true'></span></a>";
}

//User's noties creating

function errorHandling() {
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        errorNoty(jqXHR.status, jqXHR.responseText);
    });
}

function successNoty(message) {
    $("#success").css({"display" : ""});
    $("#success").html(message);
    setTimeout(function(){$("#success").css({"display" : "none"})}, 5000);
}

function errorNoty(status, respounce) {
    $("#error").css({"display" : ""});
    $("#error").html("Статус ошибки: " + status + "<br>" + respounce);
    setTimeout(function(){$("#error").css({"display" : "none"})}, 7000);
}