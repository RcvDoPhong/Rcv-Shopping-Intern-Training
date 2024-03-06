<div class="modal fade" id="modalGlobal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalTitle">User's info</h5>
                <button type="button" class="btn-close" data-modal="#modalGlobal"
                    onclick="common.hideModal(this)"></button>
            </div>
            <div class="modal-body">
                <form action="/user/users/create" method="POST" id="userCreationForm">
                    <input type="hidden" name="userRequest.userId" id="userId" value="">
                    <div id="content">
                        <div class="mt-3 pr-4">
                            <div class="row form-group">
                                <div class="col-2">
                                    <label class="mt-2">Name</label>
                                </div>
                                <div class="col-10">
                                    <input name="userRequest.userName" id="name" type="text" class="form-control" value=""
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
                                    <input name="userRequest.email" id="email" type="text" class="form-control" value="${email}"
                                        placeholder="Type email">
                                    <span name="email" id="email" class="error validation invalid-feedback d-flex"></span>
                                </div>
                            </div>
                        </div>
                        <div class="mt-3 pr-4">
                            <div class="row form-group">
                                <div class="col-2">
                                    <label class="mt-2">Group</label>
                                </div>
                                <div class="col-md">
                                    <select class="form-control" name="userRequest.groupId" id="group">
                                        <option value="-1" selected>Select group</option>
                                        <span id="ogSelectValues">
                                            <s:iterator value="groupList" var="group">
                                                <option value="${group.groupId}">
                                                    ${group.groupName}
                                                </option>
                                            </s:iterator>
                                        </span>
                                    </select>
                                    <span name="groupId" id="group" class="error validation invalid-feedback d-flex"></span>
                                </div>
                            </div>
                        </div>
                        <div class="mt-3 pr-4">
                            <div class="row form-group">
                                <div class="col-2">
                                    <label class="mt-2">Status</label>
                                </div>
                                <div class="col-md">
                                    <select class="form-control" name="userRequest.isActive" id="status">
                                        <option value="-1" selected>Select status</option>
                                        <s:iterator begin="0" end="statusList.length - 1" var="index">
                                            <option value="${index}">
                                                ${statusList[index]}
                                            </option>
                                        </s:iterator>
                                    </select>
                                    <span name="isActive" id="status" class="error validation invalid-feedback d-flex"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-modal="#modalGlobal"
                            onclick="common.hideModal(this)">Close</button>
                        <div id="additionalButtons">
                            <button type="button" class="btn btn-primary" id="submitButton" data-modal="#modalGlobal"
                                onclick="user.createUser(this)">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>