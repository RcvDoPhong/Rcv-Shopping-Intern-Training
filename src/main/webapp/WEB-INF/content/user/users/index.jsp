<%@ taglib prefix="s" uri="/struts-tags" %>

    <form action="#" method="get" id="searchForm">
        <div class="row">
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="name">Username</label>
                    <input name="name" type="text" class="form-control d-flex" id="name" value="${userName}"
                        placeholder="Type name">
                </div>
            </div>
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="email">Email</label>
                    <input name="email" type="text" class="form-control d-flex" id="email" value="${email}"
                        placeholder="Type email">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="role">Group</label>
                    <select name="group_id" class="custom-select">
                        <option value="">Select group</option>
                        <s:property value="groupList" />
                        <s:iterator value="groupList" var="group">
                            <option value="#group.groupId">
                                ${group.groupName}
                            </option>
                        </s:iterator>
                    </select>
                </div>
            </div>
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="is_active">Status</label>
                    <select name="is_active" class="custom-select" aria-label="Default
                        select example">
                        <option value="">Select status</option>
                        <!-- @foreach ($statList as $stat)
                        <option value="{{ $stat['id'] }}" @if (is_numeric(request()->input('is_active')) &&
                            intval(request()->input('is_active')) === $stat['id']) selected @endif>
                            {{ $stat['name'] }}
                        </option>
                        @endforeach -->
                    </select>
                </div>
            </div>
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="fromDate">BOD from day</label>
                    <input name="fromDate" type="date" class="form-control d-flex" value="${formDate}">
                </div>
            </div>
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="toDate">BOD to day</label>
                    <input name="toDate" type="date" class="form-control d-flex" value="${toDate}">
                </div>
            </div>
            <div class="col-auto">
                <div class="form-group">
                    <label class="mt-2 d-flex" for="updated_by">Updated by</label>
                    <select name="updated_by" class="custom-select" aria-label="Default
                        select example">
                        <option value="">Select admin</option>
                        <!-- @foreach (adminList() as $admin)
                        <option value="{{ $admin->admin_id }}" @if (intval(request()->input('updated_by')) ===
                            $admin->admin_id) selected @endif>
                            {{ $admin->name }}
                        </option>
                        @endforeach -->
                    </select>
                </div>
            </div>
        </div>
        <div class="row mb-3">
            <div class="col-md">
                <a href="#" class="btn btn-primary text-white" data-userId="" data-modal="#modalGlobal"
                    onclick="user.renderModal(this)">
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
                        onclick="common.clearSearchResult('/user/users')">
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
                    <%@ include file="../layouts/page-number.jsp" %>
                </div>
                <div class="col-4 d-flex justify-content-end">
                    Display ${(currentPage * amountForPage) - amountForPage + 1} ~ ${currentPage * amountForPage} in
                    total of ${userList.size()} users
                </div>
            </div>
        </div>
        <div class="card-body">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Updated By</th>
                        <th>Options</th>
                    </tr>
                </thead>
                <tbody id="user-table">
                    <s:if test="userList.size() > 0">
                        <s:iterator value="userList" var="user">

                            <tr>
                                <td>${user.userId}</td>
                                <td>
                                    <a href="#" class="text-decoration-none">
                                        ${user.userName}
                                    </a>
                                </td>
                                <td>${user.email}</td>
                                <td>${user.groupName}</td>
                                <td class="font-weight-bold">
                                    <s:if test="#user.isActive == 1">
                                        <span class="text-success">
                                            Active
                                        </span>
                                    </s:if>
                                    <s:else>
                                        <span class="text-danger">
                                            Locked
                                        </span>
                                    </s:else>
                                </td>
                                <td>${user.updatedByUser}</td>
                                <td>
                                    <div class="btn-group">
                                        <div>
                                            <a href="#" class="btn btn-primary text-white">
                                                <i class="fas fa-edit"></i>
                                            </a>
                                        </div>
                                        <s:if test="#session.userSession != null && #session.userSession.userId != #user.userId">
                                            <form action="/user/users/delete?userId=${user.userId}" method="POST">
                                                <button name="delete" class="btn btn-danger ml-1"
                                                    onclick="common.sweetAlertWithButton(this, event, 'Delete user','Are you sure?')">
                                                    <i class="fas fa-trash"></i>
                                                </button>
                                            </form>
                                            <form action="/user/users/lock?userId=${user.userId}" method="POST">
                                                <button name="lock" class="btn btn-dark ml-1"
                                                    onclick="common.sweetAlertWithButton(this, event, 'Lock user','Are you sure?')">
                                                    <i class="fas fa-lock"></i>
                                                </button>
                                            </form>
                                        </s:if>
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
                <%@ include file="../layouts/page-number.jsp" %>
            </div>
        </div>
    </div>
    <%@ include file="../layouts/modal-users.jsp" %>
        <!-- Will move to another place later -->
        <script src="/user/style/js/users.js"></script>