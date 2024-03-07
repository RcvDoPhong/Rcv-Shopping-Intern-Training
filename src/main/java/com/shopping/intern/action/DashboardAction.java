package com.shopping.intern.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.tiles.annotation.TilesDefinition;

@Namespace("/user")
@Result(name = "home", type = "tiles", location = "dashboard")
@TilesDefinition(name = "dashboard", extend = "masterLayout")
// @InterceptorRefs({
//         @InterceptorRef(value = "authSecureStack")
// })
public class DashboardAction {

    private String requestUrl;

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Action("dashboard")
    public String index() {
        setRequestUrl("dashboard");
        return "home";
    }
}
