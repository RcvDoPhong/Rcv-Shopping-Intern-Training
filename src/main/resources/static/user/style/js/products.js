const products = {
    handleCreateUpdateProduct: function (targetButton) {
        $(targetButton).attr("disabled", true);
        products.clearError();

        const url = $("#productForm").attr("action");
        const data = new FormData($(`form#productForm`)[0]);

        $.ajax({
            type: "POST",
            url: url,
            data: data,
            processData: false,
            contentType: false,
            success: function (response) {
                const jsonResponse = JSON.parse(response.jsonResponse.body);
                const statusCode = parseInt(response.jsonResponse.statusCodeValue);
                switch (statusCode) {
                    case 422:
                        products.displayError(jsonResponse.error);
                        break;

                    default:
                        common.sweetAlertNoButton('Success!!', jsonResponse.message, 'success', jsonResponse.url);
                        break;
                }
            },
            error: function (response) {
                console.log("error", response)
            }
        }).always(function () {
            $(targetButton).attr("disabled", false);
        })
    },
    displayError: function (errors) {
        $.each(errors, function (title, value) {
            $("form#productForm").find(`#${title}`).addClass("is-invalid");
            $(`span#${title}`).html(value);
        })
    },
    clearError: function () {
        $("form#productForm").find(".is-invalid").removeClass("is-invalid");
        $("form#productForm").find("span.validation").empty();
    }
}