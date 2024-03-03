<%@taglib prefix="s" uri="/struts-tags" %>
    <%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

        <s:form action="/user/loginAction.action" method="POST">
            <s:textfield label="email" key="email"></s:textfield><br />
            <s:password label="password" key="password"></s:password><br />
            <s:submit label="Add Information"></s:submit>
        </s:form>