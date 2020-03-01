<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Group Info</title>
    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
<h1>Group Info</h1>
<div>
    <p>Id: ${group.id}</p>
    <p>Name: ${group.name}</p>
</div>
<p>
    <a href="<c:url value="/groups"/>">Go to groups list</a>
</p>
</body>
</html>