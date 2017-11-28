<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>autorization</title>
    <style>
        <%@ include file="/resources/css/auto.css" %>
    </style>
</head>
<body>
<div class="authorization">
    <div class="header">
        <jsp:include page="fragments/header.jsp"/>
    </div>
    <form class="authorization-form" name="authorization-form" action="/login" method="post">
        <h1>Authorization</h1>
        <p><input class="authorization-field" type="text" name="username" placeholder="username"/></p>
        <p><input class="authorization-field" type="password" name="password" placeholder="password"/></p>
        <input class="sign-in-button" type="submit" value="Log in"/>
    </form>
</div>
</body>
</html>
