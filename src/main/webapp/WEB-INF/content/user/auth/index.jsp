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

                                <form method="post" action="/user/loginAction" id="loginForm">
                                    <div class="input-group mb-3">
                                        <input id="email" type="email" value="" class="form-control" name="user.email"
                                            placeholder="Email">
                                            <div class="input-group-append">
                                                <div class="input-group-text">
                                                    <span class="fas fa-envelope"></span>
                                                </div>
                                            </div>
                                            <span class="error validation invalid-feedback d-flex" id="email"></span>
                                    </div>
                                    <div class="input-group mb-3">
                                        <input id="password" type="password" class="form-control" name="user.password"
                                            placeholder="Password">
                                        <div class="input-group-append">
                                            <div class="input-group-text">
                                                <span class="fas fa-lock"></span>
                                            </div>
                                        </div>
                                        <span class="error validation invalid-feedback d-flex" id="password"></span>
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
                                        <button type="button" class="btn btn-block btn-primary" id="loginBtn"
                                        onclick="login.loginCheck(this)">
                                            Login
                                        </button>
                                    </div>
                                </form>
                                <div class="p-3 bg-danger rounded d-none" id="status">
                                    <span class="validation text-white"
                                        id="status"></span>
                                </div>
                            </div>
                            <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                    </div>
                </div>
            </div>
        </body>
        <script src="/user/style/plugins/jquery/jquery.min.js"></script>
        <script src="/user/style/js/login.js"></script>
        <script src="/user/style/js/app.js"></script>
        <script src="/user/style/plugins/sweetalert2/sweetalert2.all.min.js"></script>