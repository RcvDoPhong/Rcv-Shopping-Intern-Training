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
                    <div id="content">
                        <div class="mt-3 pr-4">
                            <div class="row form-group">
                                <div class="col-2">
                                    <label class="mt-2">Name</label>
                                </div>
                                <div class="col-10">
                                    <input name="user.userName" type="text" class="form-control" value="${userName}"
                                        placeholder="Type name">
                                    <span name="name" class="error invalid-feedback d-flex"></span>
                                </div>
                            </div>
                        </div>
                        <div class="mt-3 pr-4">
                            <div class="row form-group">
                                <div class="col-2">
                                    <label class="mt-2">Email</label>
                                </div>
                                <div class="col-10">
                                    <input name="user.email" type="text" class="form-control" value="${email}"
                                        placeholder="Type email">
                                    <span name="email" class="error invalid-feedback d-flex"></span>
                                </div>
                            </div>
                        </div>
                        <div class="mt-3 pr-4">
                            <div class="row form-group">
                                <div class="col-2">
                                    <label class="mt-2">Group</label>
                                </div>
                                <div class="col-md">
                                    <select class="form-control" name="user.groupId">
                                        <option value="">Select group</option>
                                        <span id="ogSelectValues">
                                            <s:iterator value="groupList" var="group">
                                                <option value="${group.groupId}">
                                                    ${group.groupName}
                                                </option>
                                            </s:iterator>
                                        </span>
                                    </select>
                                    <span name="groupId" class="error invalid-feedback d-flex"></span>
                                </div>
                            </div>
                        </div>
                        <div class="mt-3 pr-4">
                            <div class="row form-group">
                                <div class="col-2">
                                    <label class="mt-2">Status</label>
                                </div>
                                <div class="col-md">
                                    <select class="form-control" name="user.isActive">
                                        <option value="">Select status</option>
                                        <s:iterator begin="0" end="statusList.length - 1" var="index">
                                            <option value="${index}">
                                                ${statusList[index]}
                                            </option>
                                        </s:iterator>
                                    </select>
                                    <span name="isActive" class="error invalid-feedback d-flex"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-modal="#modalGlobal"
                            onclick="common.hideModal(this)">Close</button>
                        <div id="additionalButtons">
                            <button type="button" class="btn btn-primary" data-modal="#modalGlobal"
                                onclick="user.createUser(this)">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>