<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <a href="/user/dashboard" class="brand-link">
        <img src="https://assets.infyom.com/logo/blue_logo_150x150.png"
             alt="AdminLTE Logo"
             class="brand-image img-circle elevation-3">
        <span class="navbar-brand font-weight-light">Shopping</span>
    </a>

    <div class="sidebar">
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <li class="nav-item">
                    <a href="/user/dashboard"
                        class="nav-link <s:if test='requestURL == "dashboard"'>active</s:if>">
                        <i class="fas fa-home mr-2"></i>
                        <p>Dashboard</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="/user/users"
                        class="nav-link  <s:if test='requestURL == "users-management"'>active</s:if>">
                        <i class="fas fa-users mr-2"></i>
                        <p>Users</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#"
                        class="nav-link  <s:if test='requestURL == "products"'>active</s:if>">
                        <i class="fas fa-box mr-2"></i>
                        <p>Products</p>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</aside>