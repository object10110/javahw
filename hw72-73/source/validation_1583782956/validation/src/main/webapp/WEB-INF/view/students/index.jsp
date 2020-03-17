<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
    <style>
        .error {
            outline: 1px solid red;
        }
        .error-message {
            color: red;
        }
    </style>
</head>
<body>
<h1>Students</h1>

<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Age</th>
        <th>Group</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${students}" var="s">
        <tr>
            <td>${s.id}</td>
            <td>${s.firstName}</td>
            <td>${s.lastName}</td>
            <td>${s.age}</td>
            <td>${s.group}</td>
            <td><a href="<spring:url value="/delete/${s.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<h2>Create students</h2>
<spring:url value='/new' var="url"/>
<form:form method="post" modelAttribute="student" action="${url}">
    <div>
        <form:label path="firstName">First name: </form:label>
        <form:input path="firstName" cssErrorClass="error"/>
        <form:errors path="firstName" cssClass="error-message" />
    </div>
    <div>
        <form:label path="lastName">Last name: </form:label>
        <form:input path="lastName" cssErrorClass="error"/>
        <form:errors path="lastName" cssClass="error-message" />
    </div>
    <div>
        <form:label path="age">Age: </form:label>
        <form:input path="age" cssErrorClass="error"/>
        <form:errors path="age" cssClass="error-message" />
    </div>

    <div>
        <form:label path="group">Group: </form:label>
        <form:input path="group"  cssErrorClass="error"/>
        <form:errors path="group" cssClass="error-message" />
    </div>

    <div>
        <input type="submit"/>
    </div>
</form:form>
<%--<form method="post" action="<spring:url value="/new"/>">--%>
<%--    <div>--%>
<%--        <label for="firstName">First name: </label>--%>
<%--        <input name="firstName" id="firstName" required/>--%>
<%--    </div>--%>
<%--    <div>--%>
<%--        <label for="lastName">Last name: </label>--%>
<%--        <input name="lastName" id="lastName" required/>--%>
<%--    </div>--%>
<%--    <div>--%>
<%--        <label for="age">Age: </label>--%>
<%--        <input name="age" id="age"--%>
<%--        &lt;%&ndash;               type="number" min="8" max="60" required&ndash;%&gt;--%>
<%--        />--%>
<%--    </div>--%>

<%--    <div>--%>
<%--        <label for="group">Group: </label>--%>
<%--        <input name="group" id="group" required/>--%>
<%--    </div>--%>

<%--    <div>--%>
<%--        <input type="submit"/>--%>
<%--    </div>--%>
<%--</form>--%>
<div>${message}</div>
</body>
</html>
