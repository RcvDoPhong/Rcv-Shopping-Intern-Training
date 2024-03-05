package com.shopping.intern.action;

import java.util.ArrayList;
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
import com.shopping.intern.model.Group;
import com.shopping.intern.model.User;
import com.shopping.intern.service.Group.IGroupService;
import com.shopping.intern.service.User.IUserService;

@Namespace("/user/users")
@Results({
        @Result(name = "index", type = "tiles", location = "users") // location to indicate which tiles to us
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

    private List<User> userList;

    private List<Group> groupList;

    private String[] statusList = {"Non-Active", "Active"};

    private User user;

    private int currentPage;

    private int amountForPage = 5;

    private int totalPage;

    public UserAction(IUserService userService, IGroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    // User list
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
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
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
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
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Status list
    public String[] getStatusList() {
        return statusList;
    }

    public void setStatusList(String[] statusList) {
        this.statusList = statusList;
    }

    @Action("")
    @Override
    public String execute() {
        requestURL = "users-management";

        String page = request.getParameter("page");

        setCurrentPage(page == null ? 1 : Integer.valueOf(page));

        List<User> totalUsers = this.userService.findAll();
        setTotalPage((int) Math.ceil((double) totalUsers.size() / amountForPage));

        int limitTake = (currentPage - 1) * amountForPage;

        setUserList(this.userService.paginate(limitTake, amountForPage));
        setGroupList(this.groupService.findAll());
        return "index";
    }

    @Action("create")
    public ResponseEntity create() {
        JSONObject response = this.userService.validate(user);

        return ResponseEntity.ok(response.toString());
    }

    @Action("delete")
    public void delete() {
        long userId = Long.valueOf(request.getParameter("userId"));
        this.userService.deleteById(userId);
        System.out.println(userId);
    }

    @Action("lock")
    public void lock() {
        long userId = Long.valueOf(request.getParameter("userId"));
        this.userService.lockById(userId);
        System.out.println(userId);
    }

    @Action("logout")
    public String logout() {
        ActionContext.getContext().getSession().remove("userSession");
        return "loginRedirect";
    }
}
