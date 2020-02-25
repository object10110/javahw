<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new group</title>
    <link href="<c:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
    <h1>Create new group</h1>
<form method="post">
    <div>
        <label for="name">Name: </label>
        <input name="name" id="name" required/>
    </div>
    <div>
        <input type="submit"/>
    </div>
</form>
</body>
</html>
