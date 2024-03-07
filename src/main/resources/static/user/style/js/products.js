const products = {
    createProduct: function(targetButton, event) {
        // event.preventDefault();
        
        const form = $("#productForm");
        const url = form.attr("action");
        products.createUpdateProduct(url, form, targetButton);
    },
    createUpdateProduct: function(url, form, event, targetButton = '') {
        common.handleCreateUpdate(event, targetButton, "#productForm");
        // const data = form.serialize();
        // console.log(data, url);

        // $.ajax({
        //     type: "POST",
        //     url: url,
        //     data: data,
        //     processData: false,
        //     success: function(response) {
        //         console.log(response);
        //     }
        // })
    }
}