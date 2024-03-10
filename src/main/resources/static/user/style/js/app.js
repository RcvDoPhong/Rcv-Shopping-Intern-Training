const common = {
    returnCurrentRoute: function() {
        let url = window.location.href;
        url = url.replace('#', '');
        return url;
    },
    sweetAlertWithButton: function (target, event, title, message) {
        event.preventDefault();
        const form = $(target).closest("form");
        new swal({
            title: title,
            text: message,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: "POST",
                    data: form.serialize(),
                    url: form.attr('action')
                }).done(function (response) {
                    const jsonResponse = JSON.parse(response.jsonResponse.body);
                    common.sweetAlertNoButton('Success!!', jsonResponse.message, 'success', common.returnCurrentRoute())
                }).fail(function (error) {
                    const jsonResponse = JSON.parse(response.jsonResponse.body);
                    common.sweetAlertNoButton('Oops!!', jsonResponse.message, 'error')
                })
            }
        });
    },
    sweetAlertNoButton: function(title, message, icon, url = '') {
        Swal.fire({
            title: title,
            text: message,
            icon: icon,
            timer: 2000,
            showConfirmButton: false,
        }).then(function() {
            if (url !== '') {
                window.location.replace(url)
            }
        });
    },
    hideModal: function (target) {
        const modal = $(target).data('modal');
        $(modal).modal('hide')
    },
    clearSearchResult: function(url) {
        $('form#searchForm').find('input').val('')
        $('form#searchForm').find('select').val('')
        window.location.href = url
    }
}