<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>announcement-search</title>
    <style>
        <%@ include file="/resources/css/auto.css" %>
    </style>
    <script src="<c:url value="/resources/js/jquery.js" />"></script>
</head>
<body>
<div class="announcement-search-background">
    <div class="announcement-search">
        <div class="header">
            <jsp:include page="fragments/header.jsp"/>
        </div>
        <div class="announcement-search-middle">
            <form class="announcement-search-form" action="announcement-search" method="post">
                <h1 class="announcement-add-label">Refining search</h1>
                <div class="announcement-add-string">
                    <div class="announcement-add-text">Brand:</div>
                    <select id="brandComboBoxId" name="brandComboBoxName">
                        <option value="0">Select brand</option>
                        <c:forEach items="${brands}" var="brand">
                            <option value="${brand.name}">${brand.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="announcement-add-string">
                    <div class="announcement-add-text">Model:</div>
                    <select id="modelComboBoxId" name="modelComboBoxName"></select>
                </div>
                <div class="announcement-add-string">
                    <div class="announcement-add-text">Year:</div>
                    <select id="yearFromComboBoxId" name="yearFromComboBoxName">
                        <option value="0">From</option>
                        <c:forEach var="i" begin="0" end="116" step="1">
                            <option value="${2016 - i}">${2016 - i}</option>
                        </c:forEach>
                    </select>
                    <select id="yearToComboBoxId" name="yearToComboBoxName">
                        <option value="0">To</option>
                        <c:forEach var="i" begin="0" end="116" step="1">
                            <option value="${2016 - i}">${2016 - i}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="announcement-add-string">
                    <div class="announcement-add-text">Mileage(km):</div>
                    <input class="announcement-search-mileage" type="text" name="mileageFrom" placeholder="From"/>
                    <input class="announcement-search-mileage" type="text" name="mileageTo" placeholder="To"/>
                </div>
                <div class="announcement-add-string">
                    <div class="announcement-add-text">Engine(L):</div>
                    <input class="announcement-search-volume" type="text" name="volumeFrom" placeholder="From"/>
                    <input class="announcement-search-volume" type="text" name="volumeTo" placeholder="To"/>
                </div>
                <div class="announcement-add-string">
                    <div class="announcement-add-text">Transmission:</div>
                    <select id="TransmissionComboBoxId" name="TransmissionComboBoxName">
                        <option value="0">Transmission</option>
                        <option value="Automatic">Automatic</option>
                        <option value="Cvt">Cvt</option>
                        <option value="Mechanical">Mechanical</option>
                    </select>
                </div>
                <div class="announcement-add-string">
                    <div class="announcement-add-text">Price($):</div>
                    <input class="announcement-search-price" type="text" name="priceFrom" placeholder="From"/>
                    <input class="announcement-search-price" type="text" name="priceTo" placeholder="To"/>
                </div>


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
                <input class="announcement-search-btn" type="submit" value="search">
            </form>
        </div>
    </div>
</div>


</body>
</html>
