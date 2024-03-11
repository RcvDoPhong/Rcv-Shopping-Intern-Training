<%@ taglib prefix="s" uri="/struts-tags" %>
    <s:if test="hasActionErrors()">
        <div class="mt-2 p-3 bg-danger text-white rounded">
            <s:actionerror />
        </div>
    </s:if>
    <form action="/user/customers/" method="GET" id="searchForm">
        <div class="row">
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="customerName">Product name</label>
                    <input name="customerName" type="text" class="form-control d-flex" value="${param.customerName}"
                        placeholder="Type name">
                </div>
            </div>
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="email">Email</label>
                    <input name="email" type="text" class="form-control d-flex" value="${param.email}"
                        placeholder="Type email">
                </div>
            </div>
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="isActive">Status</label>
                    <select name="isActive" class="custom-select">
                        <option value="">Select status</option>
                        <s:iterator begin="0" end="statusList.length - 1" var="index">
                            <option value="${index}" <s:if test="#index == #parameters.isActive[0]">selected</s:if>>
                                ${statusList[index]}
                            </option>
                        </s:iterator>
                    </select>
                </div>
            </div>
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="address">Address</label>
                    <input name="address" type="text" class="form-control d-flex" value="${param.address}"
                        placeholder="Type address">
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md">
                <a href="#" class="btn btn-primary text-white" data-modal="#modalCustomer"
                    onclick="customers.renderModal(this)">
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
                        onclick="common.clearSearchResult('/user/customers/')">
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
                    total of ${customerList.size()} users
                </div>
            </div>
        </div>
        <div class="card-body">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Full name</th>
                        <th>Email</th>
                        <th>Address</th>
                        <th>Phone number</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody id="user-table">
                    <s:if test="customerList.size() > 0">
                        <s:iterator value="customerList" var="customer">
                            <tr data-id="${customer.customerId}" id="rowCustomer${customer.customerId}">
                                <form action="/user/customers/update" id="formUpdateUser${customer.customerId}">
                                    <input type="hidden" name="customerForm.customerId" id="customerId" value="${customer.customerId}">
                                    <td>${customer.customerId}</td>
                                    <td>
                                        <div>
                                            <input name="customerForm.customerName" id="name"
                                                class="form-control no-border" disabled="disabled" type="text"
                                                value="${customer.customerName}" size="8">
                                            <span class="error validation invalid-feedback d-flex" id="name"></span>
                                        </div>
                                    </td>
                                    <td>
                                        <input name="customerForm.email" id="email" class="form-control no-border"
                                            disabled="disabled" type="text" value="${customer.email}" size="10">
                                        <span class="error validation invalid-feedback d-flex" id="email"></span>
                                    </td>
                                    <td>
                                        <input name="customerForm.address" id="address" class="form-control no-border"
                                            disabled="disabled" type="text" value="${customer.address}">
                                        <span class="error validation invalid-feedback d-flex" id="address"></span>
                                    </td>
                                    <td>
                                        <input name="customerForm.telNum" id="phone" class="form-control no-border"
                                            disabled="disabled" type="text" value="${customer.telNum}" size="4">
                                        <span class="error validation invalid-feedback d-flex" id="phone"></span>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <div>
                                                <a href="#" id="enableEdit" class="btn btn-primary text-white"
                                                    data-row="#rowCustomer${customer.customerId}"
                                                    data-id="${customer.customerId}"
                                                    onclick="customers.enableEdit(this)">
                                                    <i class="fas fa-edit"></i>
                                                </a>
                                            </div>
                                            <div id="editButton" class="d-none">
                                                <button type="button" name="update" class="btn btn-success rounded"
                                                    data-row="#rowCustomer${customer.customerId}"
                                                    data-id="${customer.customerId}"
                                                    onclick="customers.updateCustomer(this)">
                                                    <i class="fas fa-check"></i>
                                                </button>
                                                <button type="button" name="cancel" class="btn btn-danger rounded ml-1"
                                                    data-row="#rowCustomer${customer.customerId}"
                                                    data-id="${customer.customerId}"
                                                    onclick="customers.sweetAlertWithButton(this, event, 'Cancel edit?')">
                                                    <i class="fas fa-window-close"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </td>
                                </form>
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
        <%@ include file="../layouts/modal-customers.jsp" %>
    </div>
    <script src="/user/style/js/customers.js"></script>