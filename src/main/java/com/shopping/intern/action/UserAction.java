package com.shopping.intern.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.tiles.annotation.TilesDefinition;
import org.apache.struts2.tiles.annotation.TilesDefinitions;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shopping.intern.model.User;
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
//     @InterceptorRef(value = "authSecureStack")
// })
public class UserAction extends ActionSupport {

    private HttpServletRequest request = ServletActionContext.getRequest();

    private IUserService userService;

    private String requestUrl;

    private List<User> userListPaginate;

    private String[] groupList = { "Admin", "Reviewer", "Editor" };

    private String[] statusList = { "Non-Active", "Active" };

    private User userRequest;

    private User userSearchForm;

    private int page;

    private String changePageAction = "/user/users/";

    private int amountForPage = 10;

    private int totalPage;

    private StringBuilder searchURL = new StringBuilder();

    private ResponseEntity<String> jsonResponse;

    private long userId;

    private String userName;

    private String email;

    private String groupRole;

    private String isActive;

    private String messageResponse;

    public UserAction(IUserService userService) {
        this.userService = userService;
    }

    // User list
    public List<User> getUserListPaginate() {
        return userListPaginate;
    }

    public void setUserListPaginate(List<User> userListPaginate) {
        this.userListPaginate = userListPaginate;
    }

    public String[] getGroupList() {
        return groupList;
    }

    public void setGroupList(String[] groupList) {
        this.groupList = groupList;
    }

    // Request URL
    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
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

    public User getUserSearchForm() {
        return userSearchForm;
    }

    public void setUserSearchForm(User userSearchForm) {
        this.userSearchForm = userSearchForm;
    }

    public StringBuilder getSearchURL() {
        return searchURL;
    }

    public void setSearchURL(StringBuilder searchURL) {
        this.searchURL = searchURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupRole() {
        return groupRole;
    }

    public void setGroupRole(String groupRole) {
        this.groupRole = groupRole;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getChangePageAction() {
        return changePageAction;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(String messageResponse) {
        this.messageResponse = messageResponse;
    }

    public void getParamsURL() {
        User tempValue = new User();
        if (userName != null) {
            tempValue.setUserName(userName.equals("") ? null : userName);
            searchURL.append("userName=" + userName + "&");
        }

        if (email != null) {
            tempValue.setEmail(email.equals("") ? null : email);
            searchURL.append("email=" + email + "&");
        }

        if (groupRole != null) {
            tempValue.setGroupRole(groupRole.equals("") ? null : groupRole);
            searchURL.append("groupRole=" + groupRole + "&");
        }

        if (isActive != null) {
            tempValue.setIsActive(isActive.equals("") ? null : isActive);
            searchURL.append("isActive=" + isActive + "&");
        }

        setUserSearchForm(tempValue);
    }

    public void setupMessageResponse(String message, long userId) {
        JSONObject response = new JSONObject();
        User userInfo = this.userService.get(userId);
        response.put("message", String.format(message, userInfo.getUserName()));
        setJsonResponse(new ResponseEntity<>(response.toString(), HttpStatus.OK));
    }

    @Action("")
    public String index() {
        setRequestUrl("users");

        String pageGet = request.getParameter("page");

        getParamsURL();
        setPage(pageGet == null ? 1 : Integer.parseInt(pageGet));

        List<User> totalUsers = this.userService.findAll(userSearchForm);
        setTotalPage((int) Math.ceil((double) totalUsers.size() / amountForPage));

        int limitTake = (page - 1) * amountForPage;

        setUserListPaginate(this.userService.paginate(limitTake, amountForPage, userSearchForm));
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
    public String delete() {
        setupMessageResponse("Delete %s successfully", userId);
        this.userService.deleteById(userId);

        return SUCCESS;
    }

    @Action("lock")
    public String lock() {
        setupMessageResponse("Lock %s successfully", userId);
        this.userService.lockById(userId);

        return SUCCESS;
    }

    @Action("logout")
    public String logout() {
        ActionContext.getContext().getSession().remove("userSession");
        return "loginRedirect";
    }
}
