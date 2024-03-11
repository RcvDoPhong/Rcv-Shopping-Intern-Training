const user = {
    resetSelectInput: function () {
        $("#userCreationForm").find("input").val("");
        $("select#status").val("");
        $("select#group").val("");
    },
    renderModal: function (targetButton) {
        user.clearError();
        user.resetSelectInput();
        $("#submitButton").attr("onclick", "user.createUser(this)");
        const modal = $(targetButton).data("modal");
        $(modal).modal('show');
    },
    createUser: function(targetButton) {
        user.createUpdateUser("/user/users/create", targetButton, "create");
    },
    updateUser: function(targetButton) {
        const userId = $("#userCreationForm").find("#userId").val();
        user.createUpdateUser("/user/users/update", targetButton, "update");
    },
    createUpdateUser: function(url, targetButton, processType) {
        $(targetButton).attr("disabled", true);
        user.clearError();
        
        let data = null;

		if (processType === "create") {
			data = $("#userCreationForm").find(".validate").serialize();
		} else {
        	data = $("#userCreationForm").serialize();
		}
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
            },
            error: function (response) {
                console.log("error", response)
            }
        }).always(function() {
            $(targetButton).attr("disabled", false);
        })
    },
    displayError: function(errors) {
        $.each(errors, function (title, value) {
            $("form#userCreationForm").find(`#${title}`).addClass("is-invalid");
            $(`span#${title}`).html(value);
        })
    },
    clearError: function () {
        $("form#userCreationForm").find(".is-invalid").removeClass("is-invalid");
        $("form#userCreationForm").find("span.validation").empty();
    },
    displayUser: function(targetButton, event) {
        event.preventDefault();

        user.resetSelectInput();
        user.clearError();

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
                    $("form#userCreationForm").find(`input#${title}`).val(value);
                    $("form#userCreationForm").find(`select#${title}`).val(value);
                })

                $("#submitButton").attr("onclick", "user.updateUser(this)");
            },
            error: function (response) {
                console.log(response);
            }
        })
    }
}