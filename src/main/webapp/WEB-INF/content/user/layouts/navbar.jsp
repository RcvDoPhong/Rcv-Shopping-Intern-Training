<nav class="main-header navbar navbar-expand navbar-dark">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i
                    class="fas fa-bars"></i></a>
        </li>
    </ul>

    <ul class="navbar-nav ml-auto">
        <li class="nav-link dropdown-toggle user-menu">
            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">
                <img src="https://assets.infyom.com/logo/blue_logo_150x150.png"
                    class="user-image img-circle elevation-2" alt="User Image">
                <span class="d-none d-md-inline">${userSession.userName}</span>
            </a>
            <ul class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
                <!-- User image -->
                <li class="user-header bg-primary">
                    <img src="https://assets.infyom.com/logo/blue_logo_150x150.png"
                        class="img-circle elevation-2" alt="User Image">
                    <p>
                        ${userSession.userName}
                        <small>Member since ${userSession.createdAt}</small>
                    </p>
                </li>
                <!-- Menu Footer-->
                <li class="user-footer">
                    <!-- <a href="#" class="btn btn-default btn-flat">Profile</a> -->
                    <!-- <a href="#" class="btn btn-default btn-flat float-right"
                        onclick="event.preventDefault(); document.getElementById('logout-form').submit();">
                        Sign out
                    </a> -->
                    <a href="/user/users/logout" class="btn btn-default btn-flat float-right">
                        Sign out
                    </a>
                </li>
            </ul>
        </li>
    </ul>
</nav>