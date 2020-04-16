<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Kevin's Auto Service Center</title>

    <%@include file="include/head.jsp"%>
</head>
<body>
<jsp:include page="include/menu.jsp"/>
<div class="container">
    <div class="row">
        <h1>Services</h1>
        <ul class="list-group">
            <li class="list-group-item">Battery Change</li>
            <li class="list-group-item">Oil Change</li>
            <li class="list-group-item">Tire Change</li>
        </ul>
    </div>
</div>
</body>
</html>