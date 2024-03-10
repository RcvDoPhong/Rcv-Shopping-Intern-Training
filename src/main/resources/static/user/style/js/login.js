const login = {
    loginCheck: function (targetButton) {
        const form = $("form#loginForm");
        login.clearError();

        $.ajax({
            type: "POST",
            url: "/user/loginAction",
            data: form.serialize(),
            success: function (response) {
                const jsonResponse = JSON.parse(response.jsonResponse.body);
                const statusCode = parseInt(response.jsonResponse.statusCodeValue);
                switch (statusCode) {
                    case 422:
                        login.displayError(jsonResponse.error);
                        break;

                    case 404:
                        $("div#status").removeClass("d-none");
                        $("span#status").html(jsonResponse.error.status[0]);
                        break;

                    default:
                        common.sweetAlertNoButton('Success!!', jsonResponse.message, 'success', jsonResponse.url);
                        break;
                }
            },
        })
    },
    displayError: function(errors) {
        $.each(errors, function (title, value) {
            $("form#loginForm").find(`input#${title}`).addClass("is-invalid");
            $(`span#${title}`).html(value);
        })
    },
    clearError: function () {
        $("form#loginForm").find("input.is-invalid").removeClass("is-invalid");
        $("form#loginForm").find("span.validation").empty();
        $("div#status").addClass("d-none");
    }
}