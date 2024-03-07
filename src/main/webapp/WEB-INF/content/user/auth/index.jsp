<%@taglib prefix="s" uri="/struts-tags" %>
    <%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

        <html lang="en">

        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Login</title>
            <%@ include file="../layouts/header.html" %>
        </head>

        <body>
            <div class="wrapper">
                <div class="hold-transition login-page">
                    <div class="login-box">
                        <!-- /.login-logo -->
                        <div class="card card-outline card-primary">
                            <div class="card-header text-center">
                                <a href="#" class="h1"><b>Admin</b>LTE</a>
                            </div>
                            <div class="card-body">
                                <p class="login-box-msg">Sign in to start you session</p>

                                <form method="post" action="/user/loginAction" name="loginForm">
                                    <div class="input-group mb-3">
                                        <input type="email" value="${email}"
                                            class="form-control" name="user.email"
                                            placeholder="Email">
                                        <%
                                            if (session.getAttribute("email") != null) {
                                                session.removeAttribute("email");
                                            }
                                        %>
                                        <div class="input-group-append">
                                            <div class="input-group-text">
                                                <span class="fas fa-envelope"></span>
                                            </div>
                                        </div>
                                        <span class="error invalid-feedback d-flex">
                                            <s:fielderror fieldName="email-error" />
                                        </span>
                                    </div>
                                    <div class="input-group mb-3">
                                        <input type="password"
                                            class="form-control" name="user.password"
                                            placeholder="Password">
                                        <div class="input-group-append">
                                            <div class="input-group-text">
                                                <span class="fas fa-lock"></span>
                                            </div>
                                        </div>
                                        <span class="error invalid-feedback d-flex">
                                            <s:fielderror fieldName="password-error" />
                                        </span>
                                    </div>
                                    <!-- <div>
                                        <div class="col-8">
                                            <div class="icheck-primary">
                                                <input type="checkbox" name="remember" id="remember">
                                                <label for="remember">
                                                    Remember
                                                </label>
                                            </div>
                                        </div>
                                    </div> -->
                                    <div class="text-center mt-2 mb-3">
                                        <button type="submit" class="btn btn-block btn-primary" id="loginBtn">
                                            Login
                                        </button>
                                    </div>
                                </form>
                                <s:if test="hasActionErrors()">
                                    <div class="pt-2 pb-1 bg-danger text-white rounded">
                                        <s:actionerror />
                                    </div>
                                </s:if>
                                <p class="mb-1">
                                    <a href="forgot-password.html">Recovery password</a>
                                </p>
                            </div>
                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                    </div>
                </div>
            </div>
        </body>