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

<div>
    Accept-Language: ${header['Accept-Language']}
    <br/>
    Locale: ${pageContext.response.locale}
</div>
<h1>Students</h1>

<table>
    <thead>
    <tr>
        <th>Id</th>
        <th><spring:message code="firstName" /></th>
        <th><spring:message code="lastName" /></th>
        <th><spring:message code="age" /></th>
        <th><spring:message code="group" /></th>
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
        <form:label path="firstName">
            <spring:message code="firstName" />
<%--            First name: --%>
        </form:label>
        <form:input path="firstName" cssErrorClass="error"/>
        <form:errors path="firstName" cssClass="error-message" />
    </div>
    <div>
        <form:label path="lastName"><spring:message code="lastName" /> </form:label>
        <form:input path="lastName" cssErrorClass="error"/>
        <form:errors path="lastName" cssClass="error-message" />
    </div>
    <div>
        <form:label path="age"><spring:message code="age" /></form:label>
        <form:input path="age" cssErrorClass="error"/>
        <form:errors path="age" cssClass="error-message" />
    </div>

    <div>
        <form:label path="group"><spring:message code="group" /></form:label>
        <form:input path="group" cssErrorClass="error"/>
        <form:errors path="group" cssClass="error-message" />
    </div>

    <div>
        <form:label path="birthDate"><spring:message code="birthDate" /></form:label>
        <form:input path="birthDate" cssErrorClass="error" type="date"/>
        <form:errors path="birthDate" cssClass="error-message" />
    </div>

    <div>
        <input type="submit"/>
    </div>
</form:form>

<div>${message}</div>
</body>
</html>
