<%--@elvariable id="student" type="org.itstep.model.Student"--%>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<html>
<head>
    <title>Create new student</title>
    <link href="<c:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
    <h1>Create new student</h1>
<form method="post">
    <input type="hidden" value="${student.id}" name="id" >
    <div>
        <label for="firstName">First name: </label>
        <input name="firstName" value="${student.firstName}" id="firstName" required/>
    </div>
    <div>
        <label for="lastName">Last name: </label>
        <input name="lastName" value="${student.lastName}" id="lastName" required/>
    </div>
    <div>
        <label for="age">Age: </label>
        <input name="age" id="age" value="${student.age}" type="number" min="8" max="60" required/>
    </div>

    <div>
        <label for="group">Group: </label>
        <input name="groupName" value="${student.group.name}" id="group" required/>
    </div>

    <div>
        <input type="submit"/>
    </div>
</form>
</body>
</html>
