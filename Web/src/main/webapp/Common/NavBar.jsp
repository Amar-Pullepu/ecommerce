<nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling-navbar">
    <div class="container">

      <!-- Brand -->
      <a class="navbar-brand waves-effect" href="/">
        <strong class="blue-text">ECommerce</strong>
      </a>

      <!-- Collapse -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- Links -->
      <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <!-- Left -->
        <ul class="navbar-nav mr-auto">
          <%-- <li class="nav-item active">
            <a class="nav-link waves-effect" href="/">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link waves-effect" href="/checkout">
            Checkout</a>
          </li> --%>
        </ul>

        <!-- Right -->
        <ul class="navbar-nav nav-flex-icons">
          <% if (request.getUserPrincipal() != null) { %>
          <li class="nav-item">
            <a href="/order-summary" class="nav-link waves-effect">
              <span class="badge red z-depth-1 mr-1" id="cart_count"> 0 </span>
              <i class="fas fa-shopping-cart"></i>
              <span class="clearfix d-none d-sm-inline-block"> Cart </span>
            </a>
          </li>
            <li><div class="dropdown">
              <a class="dropbtn clearfix d-none d-sm-inline-block"><% out.print(request.getUserPrincipal().getName()); %> </a>
              <div class="dropdown-content">
                <a href="/account/profileView">View Profile</a>
                <a href="/account/profileEdit">Edit Profile</a>
                <a href="/account/changePasswd">Change Password</a>
              </div>
            </div></li>
          <li class="nav-item">
            <a class="nav-link waves-effect" href="/account/logout">
              <span class="clearfix d-none d-sm-inline-block"> Logout </span>
            </a>
          </li>
          <% } else { %>
          <li class="nav-item">
            <a class="nav-link waves-effect" href="/account/login">
              <span class="clearfix d-none d-sm-inline-block"> Login </span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link waves-effect" href="/account/register">
              <span class="clearfix d-none d-sm-inline-block"> Signup </span>
            </a>
          </li>
          <% } %>
        </ul>
      </div>

    </div>
  </nav>