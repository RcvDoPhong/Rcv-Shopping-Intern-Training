const user = {
    resetSelectInput: function () {
        $("#userCreationForm").find("input").val("");
        $("#userCreationForm").find("select").val("-1");
    },
    renderModal: function (targetButton) {
        user.resetSelectInput();
        $("#submitButton").attr("onclick", "user.createUser(this)");
        const modal = $(targetButton).data("modal");
        $(modal).modal('show');
    },
    createUser: function(targetButton) {
        user.createUpdateUser("/user/users/create");
    },
    updateUser: function(targetButton) {
        const userId = $("#userCreationForm").find("#userId").val();
        user.createUpdateUser("/user/users/update?userId=" + userId);
    },
    createUpdateUser: function(url) {
        user.clearError();

        const data = $("#userCreationForm").serialize();
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            dataType: "json",
            success: function (response) {
                const jsonResponse = JSON.parse(response.jsonResponse.body);
                const statusCode = parseInt(response.jsonResponse.statusCodeValue);
                switch (statusCode) {
                    case 422:
                        user.displayError(jsonResponse.error);
                        break;

                    default:
                        common.sweetAlertNoButton('Success!!', jsonResponse.message, 'success', common.returnCurrentRoute())
                        break;
                }
                console.log(jsonResponse, statusCode)
            },
            error: function (response) {
                console.log("error", response)
            }
        })
    },
    displayError: function(errors) {
        $.each(errors, function (title, value) {
            console.log(`#${title}`, $("form#userCreationForm").find(`#${title}`), $(`#${title}`));
            $("form#userCreationForm").find(`#${title}`).addClass("is-invalid");
            $(`span#${title}`).html(value);
        })
    },
    clearError: function () {
        console.log("test")
        $("form#userCreationForm").find(".is-invalid").removeClass("is-invalid");
        $("form#userCreationForm").find("span.validation").empty();
    },
    displayUser: function(targetButton, event) {
        event.preventDefault();

        user.resetSelectInput();

        const modal = $(targetButton).data("modal");
        const userId = $(targetButton).data("id");
        $(modal).modal('show');

        $.ajax({
            type: "GET",
            url: "/user/users/get?userId=" + userId,
            success: function (response) {
                const jsonResponse = JSON.parse(response.jsonResponse.body);
                const data = jsonResponse.data;

                $.each(data, function(title, value) {
                    $(`input#${title}`).val(value);
                    $(`select#${title}`).val(value);
                    console.log(title, value);
                })

                $("#submitButton").attr("onclick", "user.updateUser(this)");
            },
            error: function (response) {
                console.log(response);
            }
        })
    }
}