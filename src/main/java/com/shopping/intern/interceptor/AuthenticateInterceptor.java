package com.shopping.intern.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.shopping.intern.action.LoginAction;

public class AuthenticateInterceptor implements Interceptor {

    private static final Log log = LogFactory.getLog(AuthenticateInterceptor.class);
    private static final String EMAIL_SESSION = "userSession";

    public void init() {
    }

    public void destroy() {
    }

    public String intercept(ActionInvocation invocation) throws Exception {

        final ActionContext context = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context
                .get(StrutsStatics.HTTP_REQUEST);
        HttpSession session = request.getSession(true);

        // Is there a "user" object stored in the user's HttpSession?
        Object email = session.getAttribute(EMAIL_SESSION);
        if (email == null) {
            // The user has not logged in yet.

            /* The user is attempting to log in. */
            if (invocation.getAction().getClass().equals(LoginAction.class)) {
                return invocation.invoke();
            }
            return "loginRedirect";
        } else {
            return invocation.invoke();
        }
    }
}
