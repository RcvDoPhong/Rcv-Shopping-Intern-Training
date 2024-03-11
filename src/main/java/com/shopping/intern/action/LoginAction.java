package com.shopping.intern.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.http.ResponseEntity;

import com.opensymphony.xwork2.ActionSupport;
import com.shopping.intern.model.User;
import com.shopping.intern.request.UserLoginRequest;
import com.shopping.intern.service.user.IUserService;

@Namespace(value = "/user")
@Results({
    @Result(name = "index", location = "auth/index.jsp"),
    @Result(name = "input", type = "redirectAction", params = {"actionName", "login"}),
    @Result(type = "json")
})
@InterceptorRefs({
    @InterceptorRef(value = "loginSecureStack")
})
public class LoginAction extends ActionSupport {

	private ResponseEntity<String> jsonResponse;

    private IUserService userService;

    private User user;

    public LoginAction(IUserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ResponseEntity<String> getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(ResponseEntity<String> jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    @Action(value = "loginAction", interceptorRefs = {
        @InterceptorRef(value = "defaultStack")
    })
    public String loginCheck() {
        Object userSession = ServletActionContext.getRequest().getSession().getAttribute("userSession");
        if (userSession == null && (user.getEmail() != null && user.getPassword() != null)) {
            UserLoginRequest loginRequest = new UserLoginRequest(user.getEmail(), user.getPassword());
            setJsonResponse(this.userService.validateLogin(loginRequest));

            return SUCCESS;
        }
        return "input";
    }

    @Action("login")
    public String loginView() {
        return "index";
    }
}
