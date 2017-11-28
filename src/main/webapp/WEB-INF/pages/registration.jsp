<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registration</title>
    <style>
        <%@ include file="/resources/css/auto.css" %>
    </style>
    <script><%@include file="/resources/js/validate.js"%></script>
</head>
<body>
<div class="registration">
    <div class="header">
        <jsp:include page="fragments/header.jsp"/>
    </div>
    <form class="registration-form" name = "registration-form" id="registration-form" action="/registration" method="post" onsubmit="return validateForm()">
        <h1>Registration</h1>
        <p><input class="registration-field" type="text" name="username" placeholder="Username(*)" onblur="validateUsername()"/></p>
        <span id="invalid-username" style="color:red;"></span>
        <p><input class="registration-field" type="password" name="password" placeholder="Password(*)" onblur="validatePassword()"/></p>
        <span id="invalid-password" style="color:red;"></span>
        <p><input class="registration-field" type="text" name="email" placeholder="Email(*)" onblur="validateEmail()"/></p>
        <span id="invalid-email" style="color:red;"></span>
        <p><input class="registration-field" type="text" name="firstName" placeholder="First Name"/></p>
        <p><input class="registration-field" type="text" name="lastName" placeholder="Last Name"/></p>
        <input class="sign-up-button" type="submit" value="Sign up"/>
    </form>
</div>
</body>
</html>
