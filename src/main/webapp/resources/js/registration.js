function save() {
    $.ajax({
        type: "POST",
        url: "saveUser/",
        data:$('#registrationForm').serialize(),
        success: function () {
            successNoty("Пользователь сохранен");
            window.location = "/";
        },
        error: function () {
            errorHandling();
        }
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

function errorHandling() {
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        if (jqXHR.status == 409) {
            errorNoty(409, "Пользователь с таким именем уже существует!")
        } else {
            errorNoty(jqXHR.status, jqXHR.responseText);
        }
    });
}