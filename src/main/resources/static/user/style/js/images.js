const imageValidate = {
    productImageDefault: "/user/images/products/product_placeholder.png",
    displayImage: function (target) {
        let img = this.productImageDefault;
        if (target.files[0]) {
            img = window.URL.createObjectURL(target.files[0]);
        }
        $("img#preview").attr("src", img);
    },
    clearImage: function (nameInput) {
        $(`input#${nameInput}`).val("");
        const imageDefault = imageValidate.productImageDefault;
        $('img#preview').attr("src", imageDefault);
    },
    clearImageByExistedImage: function (targetButton) {
        $(targetButton).attr("disabled", true);
        const productId = $(targetButton).data("id")

        $.ajax({
            type: "GET",
            url: "/user/products/getProduct?productId=" + productId,
            success: function (response) {
                const jsonResponse = JSON.parse(response.jsonResponse.body);
                $('img#preview').attr("src", "/user/images/products/" + jsonResponse.productImage);
            }
        }).always(function() {
            $(targetButton).attr("disabled", false);
        })
    }
}
