var ajaxGoods = "goods/";
var ajaxGood = "good/";
var ajaxPurs = "admin/purchasings/"
var datatableApi;
var datatableApiPur;
var form=$('#detailsForm');

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

$(function () {
    datatableApiPur = $("#datatablePur").DataTable({
        "ajax": {
            "url": ajaxPurs,
            "dataSrc": ""
        },
        "paging": true,
        "info": false,
        "columns": [
            {"data": "username"},
            {"data": "goodname"},
            {"data": "date"},
            {"data": "price"},
            {"data": "quantity"}
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