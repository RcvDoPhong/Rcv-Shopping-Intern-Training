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
        console.log(data);
        $.ajax({
            type: "POST",
            url: "/user/users/create",
            data: data,
            success: function(response) {
                console.log(response);
            }
        })
    }
}