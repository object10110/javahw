<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html; charset=utf-8" isELIgnored="false" %>
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
        <h1>Register</h1>
    </div>
    <c:url value="/register" var="loginVar"/>
    <form id="appointment-form" action="${loginVar}" method="POST">
        <div class="form-group">
            <label for="username">Username</label>
            <input name="username" id="username" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="firstName">First Name</label>
            <input name="firstName" id="firstName" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input name="lastName" id="lastName" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input name="email" id="email" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" class="form-control"/>
        </div>

        <sec:csrfInput/>

        <button type="submit" id="btn-save" class="btn btn-primary">Register</button>
    </form>
</div>
</body>
</html>