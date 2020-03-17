<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>

<div>
    Accept-Language: ${header['Accept-Language']}
    <br/>
    Locale: ${pageContext.response.locale}
</div>
<h1><spring:message code="title"/></h1>

<spring:url value='/new' var="url"/>
<form:form method="post" modelAttribute="user" action="${url}">
    <div>
        <form:label path="login"><spring:message code="login" /></form:label>
        <form:input path="login" cssErrorClass="error"/>
        <form:errors path="login" cssClass="error-message" />
    </div>
    <div>
        <form:label path="password"><spring:message code="password" /> </form:label>
        <form:input path="password" cssErrorClass="error"/>
        <form:errors path="password" cssClass="error-message" />
    </div>
    <div>
        <form:label path="confirmPassword"><spring:message code="confirmPassword" /></form:label>
        <form:input path="confirmPassword" cssErrorClass="error"/>
        <form:errors path="confirmPassword" cssClass="error-message" />
    </div>

    <div>
        <form:label path="email"><spring:message code="email" /></form:label>
        <form:input path="email" cssErrorClass="error"/>
        <form:errors path="email" cssClass="error-message" />
    </div>

    <div>
        <form:label path="gender"><spring:message code="gender" /></form:label>
        <form:select path="gender"><form:options/></form:select>
        <form:errors path="gender" cssClass="error-message" />
    </div>

    <div>
        <input type="submit"/>
    </div>
</form:form>

<h3>${message}</h3>

</body>
</html>
