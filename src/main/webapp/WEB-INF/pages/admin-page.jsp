<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>God</title>
</head>
<td>
<form class="brand-add-form" action="/brand-add" method="post">
    <p>Add brand:</p>
    <input type="text" name="brand" placeholder="Brand">
    <input type="submit" value="Add">
</form>
<form class="model-add-form" action="/model-add" method="post">
    <p>Add model:</p>
    <select id="brandComboBoxId" name="brandComboBoxName">
        <option value="0">Select brand</option>
        <c:forEach items="${brands}" var="brand">
            <option value="${brand.name}">${brand.name}</option>
        </c:forEach>
    </select>
    <input type="text" name="model" placeholder="Model">
    <input type="submit" value="Add">
</form>

    <form name="sendform" action="/loadImages" method="post">
    <table>
        <tr>
            <td>File description:</td>
            <td><input type="text" name="description"/></td>
        </tr>
<tr>
    <td>File to send:</td>
    <td>
        <input type="File" name="file_send">
    </td>
</tr>
</table>
<p>
    <input type="submit" value="Send">
</p>
</form>

</body>
</html>
