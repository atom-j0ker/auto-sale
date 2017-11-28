<%@ page import="com.auto.crud.UsersDAO" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>announcement-${auto.brand.name}-${auto.model.name}-edit</title>
    <style>
        <%@ include file="/resources/css/auto.css" %>
    </style>
    <script src="<c:url value="/resources/js/jquery.js" />"></script>
</head>
<body>
<div class="announcement-edit">

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
    <div class="header">
        <jsp:include page="fragments/header.jsp"/>
    </div>
    <div class="announcement-edit-middle">
        <form class="announcement-edit-form" action="/auto-announcement/edit/${auto.id}" method="post">
            <p>Your current data: ${auto.brand.name} ${auto.model.name}.</p>
            <p>If you want to change, please, choose:</p>
            <p>
                <select id="brandComboBoxId" name="brandComboBoxName">
                    <c:forEach items="${brands}" var="brand">
                        <option value="${brand.name}">${brand.name}</option>
                    </c:forEach>
                </select></p>
            <p>
            <div class="announcement-add-text">Model:</div>
            <select id="modelComboBoxId" name="modelComboBoxName">
            </select>
            </p>
            <p><input type="text" name="year" value="${auto.year}"/></p>
            <p><input type="text" name="mileage" value="${auto.mileage}"/></p>
            <p><input type="text" name="volume" value="${auto.volume}"/></p>
            <p><input type="text" name="cylinders" value="${auto.cylinders}"/></p>
            <p><input type="text" name="transmission" value="${auto.transmission}"/></p>
            <p><input type="text" name="gears" value="${auto.gears}"/></p>
            <p><input type="text" name="fuel" value="${auto.fuel}"/></p>
            <p><input type="text" name="price" value="${auto.price}"/></p>
            <p><input type="submit" value="Save changes"/></p>

            <script type="text/javascript">
                $("#brandComboBoxId").change(function () {
                    var brandName = $(this).val();
                    $.ajax({
                        type: "POST",
                        url: '/announcement-add/modelsList',
                        data: "brandName=" + brandName,
                        dataType: 'text',
                        success: function (data) {
                            display(data);
                        },
                        error: function (xhr, textStatus) {
                            alert([xhr.status, textStatus]);
                        }
                    })
                });

                function display(data) {
                    var modelElement = document.getElementById("modelComboBoxId");
                    modelElement.length = 0;
                    if (data.length != 0) {
                        var models = data.split(",");
                        modelElement.options[0] = new Option('Select model', 0);
                    }
                    modelElement.selectedIndex = 0;

                    for (var i = 0; i < models.length; i++) {
                        modelElement.options[modelElement.length] = new Option(models[i], models[i]);
                    }
                }
            </script>
        </form>
    </div>
</body>
</html>
