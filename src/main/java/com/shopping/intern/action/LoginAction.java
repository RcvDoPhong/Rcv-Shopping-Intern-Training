package com.shopping.intern.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.tiles.annotation.TilesDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import com.shopping.intern.helper.Helper;
import com.shopping.intern.helper.RouteHelper;
import com.shopping.intern.model.User;
import com.shopping.intern.request.UserLoginRequest;
import com.shopping.intern.service.User.IUserService;

// @Controller
@Namespace(value = "/user")
// @InterceptorRefs({
//     @InterceptorRef(value = "loginSecureStack")
// })
public class LoginAction extends ActionSupport {

    // What is this used for?
    private static final long serialVersionUID = 1L;

    private IUserService userService;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginAction(IUserService userService) {
        this.userService = userService;
    }

    @Action(value = "loginAction", results = {
        // @Result(name = "input", type = "redirectAction", params = {"actionName", "login"}),
        @Result(name = "input", type = "redirectAction", params = {"actionName", "login"}),
        @Result(name = "redirectPageResult", type = "redirectAction", params = {"actionName", "dashboard"})
    }, interceptorRefs = {
        @InterceptorRef(value = "store", params = {"operationMode", "STORE"}),
        @InterceptorRef(value = "defaultStack")
    })
    public String login() {
        Object userSession = ServletActionContext.getRequest().getSession().getAttribute("userSession");
        if (userSession == null && (user.getEmail() != null && user.getPassword() != null)) {
            UserLoginRequest loginRequest = new UserLoginRequest(user.getEmail(), user.getPassword());
            if (this.userService.checkLogin(loginRequest)) {
                return "redirectPageResult";
            } else {
                addActionError("Email or Password is incorrect");
            }
        }
        return "input";
    }

    @Action(value = "login", results = {
        @Result(name = "input", location = "auth/index.jsp")
    }, interceptorRefs = {
        @InterceptorRef(value = "store", params = {"operationMode", "RETRIEVE"})
    })
    public String loginView() {
        System.out.println("Email in session: " + ActionContext.getContext().getSession().get("email"));
        return "input";
    }

    @Override
    public void validate() {
        boolean errorValidate = false;
        if (user.getPassword() != null && user.getPassword().length() < 6) {
            errorValidate = true;
            addFieldError("password-error", "Password must greater or equal to 6 characters");
        }
        if (errorValidate) {
            this.userService.storeTempValueLoginFail(new UserLoginRequest(user.getEmail(), user.getPassword()));
        }
    }
}
