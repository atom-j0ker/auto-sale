<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>auto announcements</title>
    <style>
        <%@ include file="/resources/css/auto.css" %>
    </style>
    <script src="<c:url value="/resources/js/jquery.js" />"></script>
</head>
<body>
<div class="announcements">
    <div class="announcements-list">
        <div class="header">
            <jsp:include page="fragments/header.jsp"/>
        </div>
        <div id="a">
        <span style="font-size: 24px;">We have found for You: ${autos.size()} Results. </span>
        <a href="<spring:url value="/announcement-search" htmlEscape="true" />">
            <input class="refine-search" type="submit" value="Refine your search">
        </a>
        <span style="font-size: 24px;">Sort: </span>
        <select class="sort-by-combobox" id="sortByComboBoxId" name="sortByComboBoxName">
            <option value="0">Simple</option>
            <option value="1">From cheap to expensive</option>
            <option value="2">From expensive to cheap</option>
        </select>
        <script type="text/javascript">
            $("#sortByComboBoxId").change(function () {
                var sortBy = $(this).val();
                var href = window.location.href;
                if (href.indexOf("sortBy") < 0) {
                    href = href.replace("all-announcements", "announcements");
                    href += "?brandId=0&modelId=0&yearFrom=0&yearTo=0&mileageFrom=&mileageTo=&volumeFrom=&volumeTo=&transmission=0&priceFrom=&priceTo=&sortBy=0";
                }
                href = href.substr(0, href.length - 1);
                href += sortBy;

                window.location.href = href;
            });
        </script>
        <c:forEach items="${autos}" var="auto">
            <div class="announcement-auto">
                <a href="<spring:url value="/auto-announcement/{autoId}" htmlEscape="true">
                    <spring:param name="autoId" value="${auto.id}"/></spring:url>">
                    <img class="announcement-auto-photo" src="/resources/image/${auto.id}/1.jpg"/>
                </a>
                <div class="announcement-auto-info">
                    <a href="<spring:url value="/auto-announcement/{autoId}" htmlEscape="true">
                    <spring:param name="autoId" value="${auto.id}"/></spring:url>">
                        <p class="announcement-auto-name">${auto.year} ${auto.brand.name} ${auto.model.name}</p>
                    </a>
                    <p class="announcement-auto-price">&nbsp;$${auto.price}&nbsp;</p>
                    <p style="margin-bottom: 5px;">Mileage: ${auto.mileage} km</p>
                    <p style="margin-bottom: 5px;">Engine: ${auto.volume}L V${auto.cylinders}</p>
                    <p style="margin-bottom: 5px;">Transmission: ${auto.transmission} ${auto.gears}-Speed</p>
                    <p style="margin-bottom: 5px;">Fuel: ${auto.fuel}</p>
                </div>
            </div>
        </c:forEach>

        </div>
    </div>
</div>
</body>
</html>
