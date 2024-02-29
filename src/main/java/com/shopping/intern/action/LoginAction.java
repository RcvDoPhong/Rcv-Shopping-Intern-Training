package com.shopping.intern.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import com.shopping.intern.repository.User.IUserRepository;
import com.shopping.intern.request.UserLoginRequest;

public class LoginAction extends ActionSupport {

    private IUserRepository userRepo;

    private String email;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginAction(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @EmailValidator(type = ValidatorType.SIMPLE, message = "Please enter a valid email address", fieldName = "email")
    public String login() {
        Object userSession = ServletActionContext.getRequest().getSession().getAttribute("userSession");
        if (userSession == null) {
            UserLoginRequest loginRequest = new UserLoginRequest(getEmail(), getPassword());
            if (this.userRepo.checkLogin(loginRequest)) {
                return "redirectPageResult";
            } else {
                addActionError("Email or Password is incorrect");
            }
        }
        return "input";
    }

    public String loginView() {
        System.out.println("Email in session: " + ActionContext.getContext().getSession().get("email"));
        return "input";
    }

    @Override
    public void validate() {
        boolean errorValidate = false;
        if (getPassword() != null && getPassword().length() < 6) {
            errorValidate = true;
            // RedirectAttributes redirAttrs = new RedirectAttributes();
            addFieldError("password-error", "Password must greater or equal to 6 characters");
        }
        if (errorValidate) {
            this.userRepo.storeTempValueLoginFail(new UserLoginRequest(getEmail(), getPassword()));
        }
    }
}
