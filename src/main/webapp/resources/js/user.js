var ajaxGoods = "user/goods/";
var ajaxGood = "good/";
var ajaxPur = "user/purchase/";
var datatableApi;
var form=$('#basket');

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

function purchase(username) {
    $.ajax({
        type: "POST",
        url: ajaxPur + username + "/",
        data:form.serialize(),
        success: function () {
            $("#purchasing").modal("hide");
            updateTable();
            successNoty("Поздравляем с покупкой!");
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
