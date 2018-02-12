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
