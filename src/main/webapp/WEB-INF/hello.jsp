<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> -->

<!-- This tag allow to parse value from action to jsp-->
<!-- <%@ taglib prefix="s" uri="/struts-tags" %> -->

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
    </head>

    <body>
        <form:form method="POST" name="customerForm">

			<form:errors path="*" cssClass="errorblock" element="div" />

			<table>
				<tr>
					<td>Username : </td>
					<td>
						<form:input path="userName" />
					</td>
					<td>
						<form:errors path="userName" cssClass="error" />
					</td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" /></td>
				</tr>
			</table>
		</form:form>
        <!-- Hello world!
    <h1><s:property value="user.email"/></h1>
    <s:textfield name="test" value=""/> -->
    </body>

    </html>