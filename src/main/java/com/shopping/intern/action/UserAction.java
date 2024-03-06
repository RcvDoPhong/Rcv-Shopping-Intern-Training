package com.shopping.intern.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Response;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.tiles.annotation.TilesDefinition;
import org.apache.struts2.tiles.annotation.TilesDefinitions;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shopping.intern.model.Group;
import com.shopping.intern.model.User;
import com.shopping.intern.request.UserCreateUpdateRequest;
import com.shopping.intern.service.Group.IGroupService;
import com.shopping.intern.service.User.IUserService;

@Namespace("/user/users")
@Results({
        @Result(name = "index", type = "tiles", location = "users"), // location to indicate which tiles to us
        @Result(type = "json")
})
@TilesDefinitions({
        @TilesDefinition(name = "users", extend = "masterLayout")
})
// @InterceptorRefs({
// @InterceptorRef(value = "authSecureStack")
// })
public class UserAction extends ActionSupport {

    private HttpServletRequest request = ServletActionContext.getRequest();

    private IUserService userService;

    private IGroupService groupService;

    private String requestURL;

    private List<User> userListPaginate;

    private List<Group> groupList;

    private String[] statusList = { "Non-Active", "Active" };

    // private UserCreateUpdateRequest userRequest;
    private User userRequest;

    private User userSearchForm;

    private int page;

    private int amountForPage = 5;

    private int totalPage;

    private long userId;

    private String searchURL;

    private ResponseEntity<String> jsonResponse;

    public UserAction(IUserService userService, IGroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    // User list
    public List<User> getUserListPaginate() {
        return userListPaginate;
    }

    public void setUserListPaginate(List<User> userListPaginate) {
        this.userListPaginate = userListPaginate;
    }

    // Group List
    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    // Request URL
    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    // Current page
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    // Amount of users display on 1 page
    public int getAmountForPage() {
        return amountForPage;
    }

    public void setAmountForPage(int amountForPage) {
        this.amountForPage = amountForPage;
    }

    // Total page
    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    // User's info
    public User getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(User userRequest) {
        this.userRequest = userRequest;
    }

    // Status list
    public String[] getStatusList() {
        return statusList;
    }

    public void setStatusList(String[] statusList) {
        this.statusList = statusList;
    }

    public ResponseEntity<String> getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(ResponseEntity<String> jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public User getUserSearchForm() {
        return userSearchForm;
    }

    public void setUserSearchForm(User userSearchForm) {
        this.userSearchForm = userSearchForm;
    }

    public String getSearchURL() {
        return searchURL;
    }

    public void setSearchURL(String searchURL) {
        this.searchURL = searchURL;
    }

    @Action("")
    @Override
    public String execute() {
        requestURL = "users-management";

        String pageGet = request.getParameter("page");
        HttpParameters params = ActionContext.getContext().getParameters();

        // for (Object paramName : params.) {

        // }
        System.out.println(params);

        setPage(pageGet == null ? 1 : Integer.valueOf(page));

        List<User> totalUsers = this.userService.findAll();
        setTotalPage((int) Math.ceil((double) totalUsers.size() / amountForPage));

        int limitTake = (page - 1) * amountForPage;

        setUserListPaginate(this.userService.paginate(limitTake, amountForPage, userSearchForm));
        setGroupList(this.groupService.findAll());
        return "index";
    }

    @Action("create")
    public String create() {
        setJsonResponse(this.userService.validate(userRequest, "Create new user Successfully!", "create"));

        return SUCCESS;
    }

    @Action("update")
    public String update() {
        setJsonResponse(this.userService.validate(userRequest, "Update user's info Successfully!", "update"));

        return SUCCESS;
    }

    @Action("get")
    public String get() {
        setJsonResponse(this.userService.getUser(userId));
        return SUCCESS;
    }

    @Action("delete")
    public void delete() {
        long userId = Long.parseLong(request.getParameter("userId"));
        this.userService.deleteById(userId);
        System.out.println(userId);
    }

    @Action("lock")
    public void lock() {
        long userId = Long.parseLong(request.getParameter("userId"));
        this.userService.lockById(userId);
        System.out.println(userId);
    }

    @Action("logout")
    public String logout() {
        ActionContext.getContext().getSession().remove("userSession");
        return "loginRedirect";
    }
}
