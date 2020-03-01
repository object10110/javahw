<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
    <script async src="<spring:url value="/static/js/students.js"/>"></script>
</head>
<body>
<input type="hidden" id="baseUrl" value="<spring:url value="/"/>">
<h1>Students</h1>
<%--<p><a href="<spring:url value="/students/new"/>">Create student</a> </p>--%>
<h2>Create students</h2>
<div class="createContainer">
    <div>
        <label for="firstName">First name: </label>
        <input name="firstName" value="" id="firstName" required/>
    </div>
    <div>
        <label for="lastName">Last name: </label>
        <input name="lastName" value="" id="lastName" required/>
    </div>
    <div>
        <label for="age">Age: </label>
        <input name="age" value="18" id="age" type="number" min="8" max="60" required/>
    </div>
    <div>
        <label for="group">Group: </label>
        <input name="groupName" value="" id="group" required/>
    </div>
    <div>
        <button id="create">Create</button>
    </div>
</div>
    <h2>List students</h2>
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
        <%--        <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td>${student.age}</td>
                        <td>${student.group.name}</td>
                        <td><a href="<spring:url value="/students/delete/${student.id}"/>">Delete</a>
                            <br/>
                            <a href="<spring:url value="/students/update/${student.id}"/>">Update</a>
                        </td>
                    </tr>
                </c:forEach>--%>
        </tbody>
    </table>
</body>
</html>
