<h1>Login with Spring Security</h1>

<p>
This is a small application built to demonstrate Authentication and Authorization with Spring Security in a web-application context.
</p>

<h2> UI Look and Feel </h2>

<a href="https://www.youtube.com/watch?v=vWMwvbAlMEs"><img src="thumbnail.png" title="Login-with-Spring-Security"></a>

<h2>Technology Stack</h2>

<h5>
<ul>
<li> Spring Boot </li>
<li> Spring MVC </li>
<li> Spring Security </li>
<li> Spring Data JPA </li>
<li> H2 in-memory database </li>
<li> Thymeleaf </li>
<li> Bootstrap </li>
<li> Lombok </li>
</ul>
</h5>

<h5> Functionality Description </h5>

<p>
<ul>
<li><h5> Login Page </h5></li>
<p> Users can access the "/" URL to be redirected to the login page. Alternatively, users can also use the "/login" URL to be presented with the login page.
<br>They can provide the login details (username and password) and request authentication.
<br> Following validations are supported -
<ul>
<li> Username or Password incorrect </li>
<li> Incorrect password. Try again. </li>
<li> The username provided does not exist! </li>
</ul>
</ul>
<ul>
<li><h5> Forgot Password Page </h5></li>
<p> From the Login page link, users can request a new password-reset link by providing their username on the Forgot Password page.
Dummy code to send email with reset-password link.
<br> Following validations are supported -
<ul>
<li> This username does not exist </li>
</ul>
</p>
</ul>
<ul>
<li><h5> Home Page </h5></li>
<p> Authenticated users can access the Home Page.
<br>Unauthenticated users are redirected to login page if they directly access the "/home" URL.
</p>
</ul>
<ul>
<li><h5> Logout </h5></li>
<p> Authenticated Users can Logout from the Home page to the Login Page.
<br> User Session is invalidated on logout. Following message is supported for successful logout -
<ul>
<li> You have been successfully logged out ! </li>
</ul>
</p>
</ul>
</p>


