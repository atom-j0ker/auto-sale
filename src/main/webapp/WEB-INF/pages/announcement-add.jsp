<%@ page import="com.auto.crud.UsersDAO" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>new announcement</title>
    <style>
        <%@ include file="/resources/css/auto.css" %>
    </style>
    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script type="text/javascript" src="/resources/js/bootstrap-filestyle.min.js"></script>
</head>
<body>
<div class="announcement-add">

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
    %>

    <div class="header">
        <jsp:include page="fragments/header.jsp"/>
    </div>
    <div class="announcement-add-middle">
        <form class="announcement-add-form" action="/announcement-add" enctype="multipart/form-data" method="post">
            <h1 class="announcement-add-label">Creating new announcement</h1>
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
                <select id="yearComboBoxId" name="yearComboBoxName">
                    <option value="0">Select year</option>
                    <c:forEach var="i" begin="0" end="116" step="1">
                        <option value="${2016 - i}">${2016 - i}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="announcement-add-string">
                <div class="announcement-add-text">Mileage(km):</div>
                <input class="announcement-add-mileage" type="text" name="mileage"
                       onkeypress='return event.charCode >= 48 && event.charCode <= 57' placeholder="Mileage(km)"/>
            </div>
            <div class="announcement-add-string">
                <div class="announcement-add-text">Engine:</div>
                <input class="announcement-add-volume" type="text" name="volume" placeholder="Volume(L)"/>
                <input class="announcement-add-cylinders" type="text" name="cylinders" placeholder="Cylinders(V)"/>
            </div>
            <div class="announcement-add-string">
                <div class="announcement-add-text">Transmission:</div>
                <select class="transmission-add-combobox" id="transmissionComboBoxId" name="transmissionComboBoxName">
                    <option value="0">Transmission</option>
                    <option value="Automatic">Automatic</option>
                    <option value="Cvt">Cvt</option>
                    <option value="Mechanical">Mechanical</option>
                </select>
                <input class="announcement-add-gears" type="text" name="gears" placeholder="Gears"/>
            </div>
            <div class="announcement-add-string">
                <div class="announcement-add-text">Fuel:</div>
                <select id="fuelComboBoxId" name="fuelComboBoxName">
                    <option value="0">Select fuel</option>
                    <option value="Diesel">Diesel</option>
                    <option value="Gasoline">Gasoline</option>
                </select>
            </div>

            <div class="announcement-add-string">
                <div class="announcement-add-text">Drive Train:</div>
                <select id="driveTrainComboBoxId" name="driveTrainComboBoxName">
                    <option value="0">Select drive train</option>
                    <option value="4WD">4WD</option>
                    <option value="AWD">AWD</option>
                    <option value="FWD">FWD</option>
                    <option value="RWD">RWD</option>
                </select>
            </div>

            <div class="announcement-add-string">
                <div class="announcement-add-text">Color:</div>
                <select id="colorComboBoxId" name="colorComboBoxName">
                    <option value="0">Select color</option>
                    <option value="Black">Black</option>
                    <option value="White">White</option>
                </select>
            </div>

            <div class="announcement-add-string">
                <div class="announcement-add-text">Price($):</div>
                <input class="announcement-add-price" type="text" name="price" placeholder="Price($)"/>
            </div>

            <div class="announcement-add-string">
                <div class="announcement-add-text">Upload files:</div>
                <label for="files" class="browse-images">Select Image</label>
                <input id="files" class="hidden-files" type="file" accept="image/*" multiple="true">
            </div>

            <p><input class="announcement-add-btn" type="submit" value="Create"/></p>
        </form>
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
                var modelElement = document.getElementById("modelComboBoxId");
                var models = data.split(",");
                modelElement.options[0] = new Option('Select model', 0);
            }
            modelElement.selectedIndex = 0;

            for (var i = 0; i < models.length; i++) {
                modelElement.options[modelElement.length] = new Option(models[i], models[i]);
            }
        }
    </script>
</div>
</body>
</html>
