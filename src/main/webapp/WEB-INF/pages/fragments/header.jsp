<%@ page import="com.auto.crud.UsersDAO" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

    if (!username.equals("anonymousUser"))
        userId = usersDAO.getUserIdByUsername(username);
    session.setAttribute("userId", userId);
    session.setAttribute("username", username);
%>

<a href="/"><img src="/resources/image/logo_white.jpg"/></a>
<div class="right-part-user">
    <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
        <p>
            <a href="<spring:url value="/user/${userId}"></spring:url>">
                <input class="header-btn" type="submit" value="My profile">
            </a>
            <a href="<spring:url value="/logout"></spring:url>">
                <input class="header-btn" type="submit" value="Logout">
            </a>
        </p>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
        <p><a href="<spring:url value="/authorization" />">
            <input class="header-btn" type="submit" value="Sign in"/>
        </a></p>
        <p><a href="<spring:url value="/registration" />">
            <input class="header-btn" type="submit" value="Sign up"/>
        </a></p>
    </sec:authorize>
</div>