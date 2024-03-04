package com.shopping.intern.action;

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

    @Action("dashboard")
    public String index() {
        requestURL = "dashboard";
        return "home";
    }

    @Action("users")
    public String users() {
        requestURL = "users-management";

        userList = this.userService.findAll();
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
