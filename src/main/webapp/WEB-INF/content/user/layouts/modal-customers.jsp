<div class="modal fade" id="modalCustomer" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalTitle">User's info</h5>
                <button type="button" class="btn-close" data-modal="#modalCustomer"
                    onclick="common.hideModal(this)"></button>
            </div>
            <div class="modal-body">
                <form action="/user/customers/create" method="POST" id="customerCreationForm">
                    <div id="content">
                        <div class="mt-3 pr-4">
                            <div class="row form-group">
                                <div class="col-2">
                                    <label class="mt-2">Name</label>
                                </div>
                                <div class="col-10">
                                    <input name="customerForm.customerName" id="name" type="text" class="form-control" value=""
                                        placeholder="Type name">
                                    <span name="name" id="name" class="error validation invalid-feedback d-flex"></span>
                                </div>
                            </div>
                        </div>
                        <div class="mt-3 pr-4">
                            <div class="row form-group">
                                <div class="col-2">
                                    <label class="mt-2">Email</label>
                                </div>
                                <div class="col-10">
                                    <input name="customerForm.email" id="email" type="text" class="form-control" value=""
                                        placeholder="Type email">
                                    <span name="email" id="email" class="error validation invalid-feedback d-flex"></span>
                                </div>
                            </div>
                        </div>
                        <div class="mt-3 pr-4">
                            <div class="row form-group">
                                <div class="col-2">
                                    <label class="mt-2">Phone number</label>
                                </div>
                                <div class="col-10">
                                    <input name="customerForm.telNum" id="phone" type="text" class="form-control" value=""
                                        placeholder="Type phone number">
                                    <span name="phone" id="phone" class="error validation invalid-feedback d-flex"></span>
                                </div>
                            </div>
                        </div>
                        <div class="mt-3 pr-4">
                            <div class="row form-group">
                                <div class="col-2">
                                    <label class="mt-2">Address</label>
                                </div>
                                <div class="col-10">
                                    <input name="customerForm.address" id="address" type="text" class="form-control" value=""
                                        placeholder="Type address">
                                    <span name="address" id="address" class="error validation invalid-feedback d-flex"></span>
                                </div>
                            </div>
                        </div>
                        <div class="mt-3 pr-4">
                            <div class="row form-group">
                                <div class="col-2">
                                    <label class="mt-2">Status</label>
                                </div>
                                <div class="col-md">
                                    <select class="form-control" name="customerForm.isActive" id="status">
                                        <option value="" selected>Select status</option>
                                        <s:iterator begin="0" end="statusList.length - 1" var="index">
                                            <option value="${index}">
                                                ${statusList[index]}
                                            </option>
                                        </s:iterator>
                                    </select>
                                    <span name="status" id="status" class="error validation invalid-feedback d-flex"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-modal="#modalCustomer"
                            onclick="common.hideModal(this)">Close</button>
                        <div id="additionalButtons">
                            <button type="button" class="btn btn-primary" id="submitButton" data-modal="#modalCustomer"
                                onclick="customers.createCustomer(this)">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>