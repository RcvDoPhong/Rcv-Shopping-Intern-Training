package com.shopping.intern.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.tiles.annotation.TilesDefinition;
import org.apache.struts2.tiles.annotation.TilesDefinitions;
import org.apache.struts2.tiles.annotation.TilesPutAttribute;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shopping.intern.model.User;
import com.shopping.intern.service.IUserService;

@Namespace("/user")
@Results({
        @Result(name = "home", type = "tiles", location = "dashboard"),
        @Result(name = "index", type = "tiles", location = "users") // location to indicate which tiles to use
})
@TilesDefinitions({
        @TilesDefinition(name = "dashboard", extend = "masterLayout"),
        @TilesDefinition(name = "users", extend = "masterLayout")
})
// @InterceptorRefs({
//         @InterceptorRef(value = "authSecureStack")
// })
public class UserAction extends ActionSupport {

    private IUserService userService;

    private String requestURL;

    private List<User> userList;

    private int currentPage;

    private int amountForPage = 5;

    private int totalPage;

    public UserAction(IUserService userService) {
        this.userService = userService;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getAmountForPage() {
        return amountForPage;
    }

    public void setAmountForPage(int amountForPage) {
        this.amountForPage = amountForPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Action("dashboard")
    public String index() {
        requestURL = "dashboard";
        return "home";
    }

    @Action("users")
    public String users() {
        requestURL = "users-management";

        String page = ServletActionContext.getRequest().getParameter("page");

        currentPage = page == null ? 0 : Integer.valueOf(page);

        ArrayList<User> totalUsers = this.userService.findAll();
        totalPage = (int) Math.ceil((double) totalUsers.size() / amountForPage);

        userList = this.userService.paginate(currentPage);
        currentPage = currentPage + 1;
        for (User user : userList) {
            System.out.println(user.getEmail());
        }
        return "index";
    }

    @Action("logout")
    public String logout() {
        ActionContext.getContext().getSession().remove("userSession");
        return "loginRedirect";
    }
}
