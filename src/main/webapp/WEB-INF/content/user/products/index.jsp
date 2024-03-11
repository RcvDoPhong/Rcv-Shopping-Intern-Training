<%@ taglib prefix="s" uri="/struts-tags" %>
    <s:if test="hasActionErrors()">
        <div class="mt-2 p-3 bg-danger text-white rounded">
            <s:actionerror />
        </div>
    </s:if>
    <form action="/user/products/" method="GET" id="searchForm">
        <div class="row">
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="name">Product name</label>
                    <input name="productName" type="text" class="form-control d-flex" value="${param.productName}"
                        placeholder="Type name">
                </div>
            </div>
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="is_active">Status</label>
                    <select name="isSales" class="custom-select">
                        <option value="">Select status</option>
                        <s:iterator begin="0" end="statusList.length - 1" var="index">
                            <option value="${index}" <s:if test="#index == #parameters.isSales[0]">selected</s:if>>
                                ${statusList[index]}
                            </option>
                        </s:iterator>
                    </select>
                </div>
            </div>
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="email">From Price</label>
                    <input name="fromPrice" type="text" class="form-control d-flex" value="${param.fromPrice}"
                        placeholder="Type from price range">
                </div>
            </div>
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="email">To Price</label>
                    <input name="toPrice" type="text" class="form-control d-flex" value="${param.toPrice}"
                        placeholder="Type to price range">
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md">
                <a href="/user/products/create" class="btn btn-primary text-white">
                    <i class="fas fa-plus mr-2"></i>
                    <span>Add new</span>
                </a>
            </div>
            <div class="row col-md d-flex justify-content-end">
                <div class="col-auto">
                    <button name="" type="submit" class="btn btn-primary">
                        <i class="fas fa-search mr-2"></i>
                        Search
                    </button>
                </div>
                <div class="col-auto">
                    <button name="clear" type="button" class="btn btn-success"
                        onclick="common.clearSearchResult('/user/products/')">
                        <i class="fas fa-window-close mr-2"></i>
                        Clear
                    </button>
                </div>
            </div>
        </div>
    </form>
    <div class="card">
        <div class="card-header">
            <div class="row">
                <div class="col-8 d-flex justify-content-start">
                    <s:if test="totalPage > 1">
                        <%@ include file="../layouts/page-number.jsp" %>
                    </s:if>
                </div>
                <div class="col-4 d-flex justify-content-end">
                    Display ${(page * perPage) - perPage + 1} ~ ${page * perPage} in
                    total of ${productListPaginate.size()} users
                </div>
            </div>
        </div>
        <div class="card-body">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Product UID</th>
                        <th>Product Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody id="user-table">
                    <s:if test="productListPaginate.size() > 0">
                        <s:iterator value="productListPaginate" var="product">
                            <tr>
                                <td>${product.getProductIndex()}</td>
                                <td data-toggle="tooltip" id="productId" data-html="true"
                                    title="<img class='img-thumbnail' src='/user/images/${product.productImage}'/>"
                                    onmouseover="tooltip.showContent(this)">
                                    ${product.productId}
                                </td>
                                <td>
                                    <a href="/user/products/edit?productId=${product.productId}"
                                        class="text-decoration-none">
                                        ${product.productName}
                                    </a>
                                </td>
                                <td>${product.cutDownDescriptionLength()}</td>
                                <td>$${product.productPrice}</td>
                                <td class="font-weight-bold">
                                    <s:if test="#product.isSales == 1">
                                        <span class="text-success">
                                            On sales
                                        </span>
                                    </s:if>
                                    <s:elseif test="#product.isSales == 2">
                                        <span class="text-success">
                                            Out of stock
                                        </span>
                                    </s:elseif>
                                    <s:else>
                                        <span class="text-danger">
                                            Stop production
                                        </span>
                                    </s:else>
                                </td>
                                <td>
                                    <div class="btn-group">
                                        <div>
                                            <a href="/user/products/edit?productId=${product.productId}"
                                                class="btn btn-primary text-white">
                                                <i class="fas fa-edit"></i>
                                            </a>
                                        </div>
                                        <form action="/user/products/delete?productId=${product.productId}"
                                            method="POST">
                                            <button name="delete" class="btn btn-danger ml-1"
                                                onclick="common.sweetAlertWithButton(this, event, 'Delete product','Are you sure?')">
                                                <i class="fas fa-trash"></i>
                                            </button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        <tr>
                            <td colspan=10>
                                <div class="text-center">
                                    No data found
                                </div>
                            </td>
                        </tr>
                    </s:else>
                </tbody>
            </table>
        </div>
        <div class="card-footer">
            <div class="d-flex justify-content-end">
                <s:if test="totalPage > 1">
                    <%@ include file="../layouts/page-number.jsp" %>
                </s:if>
            </div>
        </div>
    </div>