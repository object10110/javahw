<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<c:set var="title" value="Students" />
<%@include file="../include/header.jsp"%>

<div class="container">
    <h1>${title}</h1>
    <table class="highlight">
        <thead>
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Edit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teachersDto}" var="t">
            <tr>
                <td>${t.id}</td>
                <td>${t.firstName}</td>
                <td>${t.lastName}</td>
                <td>
                    <a href="<spring:url value="/teachers/edit/${t.id}"/>">Update</a>
                    <a href="<spring:url value="/teachers/delete/${t.id}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="<spring:url value="/teachers/create"/>" class="btn-floating btn-large">
        <i class="material-icons">add</i>
    </a>

    <jsp:include page="../include/scripts.jsp"/>
</div>
<%@include file="../include/footer.jsp"%>
