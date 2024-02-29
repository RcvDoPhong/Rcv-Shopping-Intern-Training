package com.shopping.intern.action;

import com.opensymphony.xwork2.ActionContext;

public class UserAction {
    public String dashboard() {
        return "home";
    }

    public String logout() {
        ActionContext.getContext().getSession().remove("userSession");
        return "loginRedirect";
    }
}
