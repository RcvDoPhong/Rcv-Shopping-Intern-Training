const imageValidate = {
    productImageDefault: "/user/images/products/product_placeholder.png",
    displayImage: function (target) {
        let img = this.productImageDefault;
        if (target.files[0]) {
            img = window.URL.createObjectURL(target.files[0]);
        }
        $("img#preview").attr("src", img);
    },
    clearImage: function (targetButton) {
        $(targetButton).val("");
        const imageDefault = imageValidate.productImageDefault;
        $('img#preview').attr("src", imageDefault);
    },
    clearImageByExistedImage: function (button, targetButton) {
        $(targetButton).val("");
        $(button).attr("disabled", true);
        const productId = $(button).data("id")

        $.ajax({
            type: "GET",
            url: "/user/products/getProduct?productId=" + productId,
            success: function (response) {
                const jsonResponse = JSON.parse(response.jsonResponse.body);
                $('img#preview').attr("src", "/user/images/" + jsonResponse.productImage);
            }
        }).always(function () {
            $(button).attr("disabled", false);
        })
    }
}
