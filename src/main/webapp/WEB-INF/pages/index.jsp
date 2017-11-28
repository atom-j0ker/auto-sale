<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="com.auto.crud.UsersDAO" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>auto</title>
    <style>
        <%@ include file="/resources/css/auto.css" %>
    </style>
</head>
<body>

<%
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UsersDAO usersDAO = WebApplicationContextUtils.getWebApplicationContext(application).getBean(UsersDAO.class);
    String username;
    long userId = 0;

    if (principal instanceof UserDetails) {
        username = ((UserDetails) principal).getUsername();
    } else {
        username = principal.toString();
    }

    if(!username.equals("anonymousUser"))
        userId = usersDAO.getUserIdByUsername(username);
    session.setAttribute("userId", userId);
%>

<div class="index-body">
    <div class="left-part">
        <img class="logo-black" src="/resources/image/logo_black.jpg">

        <div class="show-announcements-btn">
            <a href="<spring:url value="/all-announcements" htmlEscape="true" />">
                <input class="button" type="submit" value="Show announcements"/></a>
        </div>
        <div class="add-announcement-btn">
            <p>
                <a href="<spring:url value="/announcement-add" htmlEscape="true"/>">
                    <input class="button" type="submit" value="Add announcement"/>
                </a>
            </p>
        </div>
    </div>
    <div class="right-part">
        <div class="log-in-registration-form">
            <form action="/login" method="post">
                <p><input class="log-in-field" type="text" name="username" placeholder="username"/></p>
                <p><input class="log-in-field" type="password" name="password" placeholder="password"/></p>
                <input class="log-in-btn" type="submit" value="Log in"/>
            </form>
            <p style="color: white">or</p>
            <a href="<spring:url value="/registration" htmlEscape="true" />">
                <input class="log-in-btn" type="submit" value="Sign up"/>
            </a>
        </div>
    </div>
    <%
        if (!username.equals("anonymousUser")) {
    %>
    <a href="<spring:url value="/user/{userId}" htmlEscape="true"><spring:param name="userId" value="${userId}"/></spring:url>">
        <img class="my-profile-btn" src="/resources/image/my-profile.png">
    </a>
    <% } %>
    <%
        if (username.equals("admin")) {
    %>
    <a href="<spring:url value="/admin-page" htmlEscape="true" />">
        <input class="admin-btn" type="submit" value="OMG! It's admin">
    </a>
    <% } %>
</div>
</body>
</html>
