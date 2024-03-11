package com.shopping.intern.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.shopping.intern.action.LoginAction;

public class GuestInterceptor implements Interceptor {

    private String USER_SESSION = "userSession";

    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
//        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext()
//               .get(StrutsStatics.HTTP_REQUEST);
//
//        HttpSession session = request.getSession();
//        Object userSession = session.getAttribute(USER_SESSION);
//        if (userSession == null) {
//            if (invocation.getAction().getClass().equals(LoginAction.class)) {
//                return invocation.invoke();
//            }
//            return "loginRedirect";
//        }
//        return "homeRedirect";

        return invocation.invoke();
    }

}
