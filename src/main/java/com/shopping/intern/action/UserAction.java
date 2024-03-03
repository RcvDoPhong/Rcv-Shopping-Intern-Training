package com.shopping.intern.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;

@Namespace("/user")
@InterceptorRefs({
    @InterceptorRef(value = "authSecureStack")
})
public class UserAction {

    @Action(value = "dashboard", results = {
        @Result(name = "home", location = "auth/dashboard.jsp")
    })
    public String dashboard() {
        return "home";
    }

    @Action(value = "logout")
    public String logout() {
        ActionContext.getContext().getSession().remove("userSession");
        return "loginRedirect";
    }
}
