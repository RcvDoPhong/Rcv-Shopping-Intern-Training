package com.shopping.intern.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.tiles.annotation.TilesDefinition;

@Namespace("/user")
@Result(name = "home", type = "tiles", location = "dashboard")
@TilesDefinition(name = "dashboard", extend = "masterLayout")
// @InterceptorRefs({
// @InterceptorRef(value = "authSecureStack")
// })
public class DashboardAction {

    private String requestURL;

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
}
