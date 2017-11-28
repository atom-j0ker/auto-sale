<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>my-profile</title>
    <style>
        <%@ include file="/resources/css/auto.css" %>
    </style>
</head>
<body>
<div class="my-profile">
    <div class="header">
        <jsp:include page="fragments/header.jsp"/>
    </div>
    <div class="my-profile-middle">
        <div class="my-profile-form">
            <div class="my-profile-photo"></div>
            <div class="my-profile-info">
                <p>${user.firstName} ${user.lastName}</p>
                <p>${user.phone}</p>
            </div>
            <h2 style="text-align: center">My announcements:</h2>
            <table class="my-profile-announcements-form">
                <c:set var="count" value="0"></c:set>
                <c:forEach items="${autos}" var="auto">
                    <c:if test="${count % 2 == 0}">
                        <tr></tr>
                    </c:if>
                    <td class="my-profile-announcement">
                        <a href="<spring:url value="/auto-announcement/{autoId}" htmlEscape="true"><spring:param name="autoId" value="${auto.id}"/></spring:url>">
                            <img class="my-profile-auto-photo" src="/resources/image/${auto.id}/1.jpg"/>
                        </a>
                        <a href="<spring:url value="/auto-announcement/{autoId}" htmlEscape="true"><spring:param name="autoId" value="${auto.id}"/></spring:url>">
                            <p>${auto.year} ${auto.brand.name} ${auto.model.name}</p>
                        </a>
                        <p>
                            <a href="<spring:url value="/auto-announcement/edit/{autoId}" htmlEscape="true"><spring:param name="autoId" value="${auto.id}"/></spring:url>">
                                <input class="my-profile-edit-delete-btn" type="submit" value="Edit"/>
                            </a>
                        <form action="/auto-announcement/delete/${auto.id}" method="post">
                            <input class="my-profile-edit-delete-btn" type="submit" value="Delete"/>
                        </form>
                        </p>
                        <c:set var="count" value="${count + 1}"></c:set>
                    </td>
                </c:forEach>

            </table>
        </div>
    </div>
</div>
</body>
</html>
