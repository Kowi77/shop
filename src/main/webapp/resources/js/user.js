var ajaxGoods = "goods/";
var ajaxGood = "good/";
var ajaxPur = "user/purchase/";
var datatableApi;
var form=$('#bucket');

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

function choise(id) {
    $("#modalTitle").html("Корзина");
    $.get(ajaxGood + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#purchasing').modal();
    });
}

function purchase() {
    $.ajax({
        type: "POST",
        url: ajaxPur + "Zlatan/",
        data:form.serialize(),
        success: function () {
            $("#purchasing").modal("hide");
            updateTable();
            successNoty("Поздравляем с покупкой!");
        }
    });
}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
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
                "render": renderPurBtn
            }
        ],
        "order": [[0,"asc"]],
        "initComplete": errorHandling
    });
});

function renderPurBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='choise(" + row.id + ");'>" +
            "<span class='glyphicon glyphicon-shopping-cart' aria-hidden='true'></span></a>";
    }
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