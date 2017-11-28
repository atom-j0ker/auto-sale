<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${auto.year} ${auto.brand.name} ${auto.model.name}</title>
    <style>
        <%@ include file="/resources/css/auto.css" %>
    </style>
    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/slider.js" />"></script>
</head>
<body>
<div class="auto-announcement">
    <div class="header">
        <jsp:include page="fragments/header.jsp"/>
    </div>
    <div class="auto-announcement-middle">
        <p class="auto-announcement-head">
            <span class="auto-announcement-name">${auto.brand.name} ${auto.model.name} ${auto.year}</span>
            <span class="auto-announcement-price"> &nbsp; $${auto.price} / &#8372;${auto.price * 26} &nbsp;</span>
        </p>
        <div class="auto-announcement-content">

            <div class="slider">
                <div class="next"> &nbsp;></div>
                <div class="prev"> &nbsp;<</div>
                <div class="slides">
                    <c:forEach begin="1" end="${auto.images}" var="imageId">
                        <div class="slide">
                            <img src="/resources/image/${auto.id}/${imageId}.jpg"/>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="auto-announcement-characteristics">
                <p class="auto-announcement-characteristics-string">Mileage: ${auto.mileage} km</p>
                <p class="auto-announcement-characteristics-string">Engine: ${auto.volume}L V${auto.cylinders}</p>
                <p class="auto-announcement-characteristics-string">Transmission: ${auto.transmission} ${auto.gears}-Speed</p>
                <p class="auto-announcement-characteristics-string">Fuel: ${auto.fuel}</p>
                <p class="auto-announcement-characteristics-string">Info: ${auto.info}</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
