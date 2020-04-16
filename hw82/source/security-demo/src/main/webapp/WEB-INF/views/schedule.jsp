<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Kevin's Auto Service Center</title>

    <%@include file="include/head.jsp" %>

</head>
<body>
<jsp:include page="include/menu.jsp"/>
<div class="container">
    <div class="row">
        <h1>Employee Schedule</h1>
        <ul class="list-group">
            <li class="list-group-item">Kevin 8am - 4pm</li>
            <li class="list-group-item">Joey 7am - 3pm</li>
            <li class="list-group-item">Gary 10am - 1pm</li>
        </ul>
    </div>
</div>
</body>
</html>