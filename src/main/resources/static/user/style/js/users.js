const user = {
    renderModal: function (targetButton) {
        const userId = $(targetButton).data("userId");
        const modal = $(targetButton).data("modal");

        if (userId) {
            //
        }

        $(modal).modal('show');
    },
    createUser: function(targetButton) {
        const data = $("#userCreationForm").serialize();
        $.ajax({
            type: "POST",
            url: "/user/users/create",
            data: data,
            dataType: "json",
            success: function (response) {
                const jsonResponse = JSON.parse(response.jsonResponse.body);
                const statusCode = parseInt(response.jsonResponse.statusCodeValue);
                switch (statusCode) {
                    case 422:
                        console.log(jsonResponse.error);
                        break;
                
                    default:
                        console.log(jsonResponse)
                        break;
                }
                console.log(jsonResponse, statusCode)
            }
        })
    }
}