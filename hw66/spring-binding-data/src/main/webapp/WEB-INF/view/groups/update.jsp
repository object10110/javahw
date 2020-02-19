<%--@elvariable id="group" type="org.itstep.model.Group"--%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<html>
<head>
    <title>Create new group</title>
    <link href="<c:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
<h1>Create new group</h1>
<form method="post">
    <input type="hidden" value="${group.id}" name="id">
    <div>
        <label for="name">Name: </label>
        <input name="name" value="${group.name}" id="name" required/>
    </div>
    <div>
        <input type="submit"/>
    </div>
</form>
</body>
</html>