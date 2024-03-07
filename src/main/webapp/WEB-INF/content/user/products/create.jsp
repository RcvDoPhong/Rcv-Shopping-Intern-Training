<form enctype="multipart/form-data" method="POST" action="/user/products/store" id="productForm">
    <div class="row media">
        <div class="col-6">
            <div class="form-group">
                <label class="mt-2 d-flex">Product name</label>
                <input name="productForm.productName" id="name" type="text" class="form-control d-flex" value=""
                    placeholder="Type product name">
                <span class="error invalid-feedback"></span>
            </div>
            <div class="form-group">
                <label class="mt-2 d-flex">Product price</label>
                <input name="productForm.productPrice" id="price" type="number" class="form-control d-flex" value=""
                    placeholder="Type product price">
                <span class="error invalid-feedback"></span>
            </div>
            <div class="form-group">
                <label class="mt-2 d-flex">Description</label>
                <textarea class="form-control" name="productForm.description" rows="3"
                    placeholder="type description"></textarea>
                <span class="text-danger" style="font-size:80% !important"></span>
            </div>
            <div class="form-group">
                <label class="mt-2">Status</label>
                <select name="productForm.isSales" class="custom-select" id="status">
                    <option value="-1">Select status</option>
                    <s:iterator begin="0" end="statusList.length - 1" var="index">
                        <option value="${index}" <s:if test="#index == #parameters.isSales[0]">selected</s:if>>
                            ${statusList[index]}
                        </option>
                    </s:iterator>
                </select>
                <span class="text-danger" style="font-size:80% !important"></span>
            </div>
        </div>
        <div class="row col-6 mt-5">
            <div class="d-flex" style="height:300px">
                <img name="preview" class="rounded float-right img-thumbnail img-fluid"
                    src="/user/images/product_placeholder.png" alt="">
            </div>
            <div class="input-group">
                <div class="input-group-append">
                    <button class="btn btn-danger rounded" name="clear-directory" type="button" name="clearDirectory">
                        Remove file
                    </button>
                </div>
                <!-- <input class="form-control" type="file" name="upload"
                    accept="image/png, image/jpg, image/jpeg"> -->
                <input class="form-control" type="file" name="file"
                    accept="image/png, image/jpg, image/jpeg">
            </div>
            <span class="text-danger" style="font-size:80% !important"></span>
        </div>
    </div>
    <div class="row justify-content-end">
        <div class="col-auto">
            <button name="clear" type="button" class="btn btn-danger">
                <i class="fas fa-window-close mr-2"></i>
                Cancel
            </button>
        </div>
        <div class="col-auto">
            <button name="clear" type="button" class="btn btn-primary"
                onclick="products.createProduct(this, event)">
                <i class="fas fa-check mr-2"></i>
                Save
            </button>
        </div>
    </div>
</form>
<script src="/user/style/js/products.js"></script>