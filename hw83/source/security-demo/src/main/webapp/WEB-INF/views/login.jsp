<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Kevin's Auto Service Center</title>

    <%@include file="include/head.jsp"%>

</head>
<body>
<jsp:include page="include/menu.jsp"/>
<div class="container">
    <div class="row">
        <h1>Services</h1>
    </div>
    <form id="appointment-form" method="post" action="<spring:url value="/login"/>">
        <div class="form-group">
            <label for="username">Username</label>
            <input name="custom_username" id="username" class="form-control"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="custom_password" id="password" class="form-control"/>
        </div>
        <div class="form-group">
            <input type="checkbox" name="remember-me" id="remember-me"/>
            <label for="remember-me">Remember me</label>
        </div>
        <security:csrfInput/>
        <button type="submit" id="btn-save" class="btn btn-primary">Login</button>
    </form>
    <div>
        <c:if test="${param.error == 'credentials'}">
            Invalid username or password
        </c:if>
        <c:if test="${param.logout == 'success'}">
            Logged out successfully
        </c:if>
    </div>
</div>
</body>
</html>