const customers = {
    renderModal: function (targetButton) {
        customers.clearError();
        $("select#status").val("");
        const modal = $(targetButton).data("modal");
        $(modal).modal('show');
    },
    createCustomer: function (targetButton) {
        customers.handleCreateUpdateCustomer("/user/customers/create", $("form#customerCreationForm"), targetButton);
    },
    updateCustomer: function (targetRow) {
        const customerId = $(targetRow).data("id");
        const form = $(`#formUpdateUser${customerId}`);
        const url = form.attr("action");
        customers.handleCreateUpdateCustomer(url, form, targetRow, false);
    },
    handleCreateUpdateCustomer: function (url, form, targetButton, redirect = true) {
        $(targetButton).attr('disabled', true);
        customers.clearError($(targetButton).data("row"));

        $.ajax({
            type: "POST",
            url: url,
            data: form.serialize(),
            success: function (response) {
                const jsonResponse = JSON.parse(response.jsonResponse.body);
                const statusCode = parseInt(response.jsonResponse.statusCodeValue);

                let redirectPage = redirect ? common.returnCurrentRoute() : "";
                switch (statusCode) {
                    case 422:
                        customers.displayError(jsonResponse.error, $(targetButton).data("row"));
                        break;

                    default:
                        if (!redirectPage) {
                            customers.cancelEdit(targetButton);
                        }
                        common.sweetAlertNoButton('Success!!', jsonResponse.message, 'success', redirectPage)
                        break;
                }
            }
        }).always(function () {
            $(targetButton).attr('disabled', false);
        })
    },
    displayError: function (errors, row = null) {
        $.each(errors, function (title, value) {
            if (row) {
                $(row).find(`#${title}`).addClass("is-invalid");
                $(row).find(`span#${title}`).html(value);
            } else {
                $(`span#${title}`).html(value);
                $("form#customerCreationForm").find(`#${title}`).addClass("is-invalid");
            }
        })
    },
    clearError: function (row = null) {
        if (row) {
            $(row).find(".is-invalid").removeClass("is-invalid");
            $(row).find("span.validation").empty();
        } else {
            $("form#customerCreationForm").find(".is-invalid").removeClass("is-invalid");
            $("form#customerCreationForm").find("span.validation").empty();
        }
    },
    enableEdit: function (targetRow) {
        customers.handelEditState(targetRow, true);
    },
    handelEditState: function (targetRow, enable) {
        const target = $(targetRow).data("row");
        const rows = $(target).find("input");
        $.each(rows, function (index, row) {
            if (enable) {
                $(row).addClass("input-row-bg text-white");
                $(row).attr('disabled', false);
                $(row).removeClass("no-border");
            } else {
                $(row).removeClass("input-row-bg text-white");
                $(row).attr('disabled', true);
                $(row).addClass("no-border");
            }
        })

        if (enable) {
            $(target).find("#enableEdit").addClass("d-none");
            $(target).find("#editButton").removeClass("d-none");
        } else {
            $(target).find("#enableEdit").removeClass("d-none");
            $(target).find("#editButton").addClass("d-none");
        }
    },
    cancelEdit: function (targetRow) {
        const target = $(targetRow).data("row");
        const rows = $(target).find("input");
        customers.clearError(target);

        customers.handelEditState(targetRow, false);

        const customerId = $(targetRow).data("id");
        $.get("/user/customers/getCustomer?customerId=" + customerId, function(response) {
            const jsonResponse = JSON.parse(response.jsonResponse.body);
            $.each(rows, function(index, row) {
                const fieldName = $(row).attr("id");
                $(row).val(jsonResponse[fieldName]);
            })
        });
    },
    sweetAlertWithButton: function (target, event, title) {
        event.preventDefault();
        new swal({
            title: title,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
        }).then((result) => {
            if (result.isConfirmed) {
                customers.cancelEdit(target);
            }
        });
    }
}