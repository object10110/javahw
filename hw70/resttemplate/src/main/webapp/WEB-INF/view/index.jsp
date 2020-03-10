<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<body>
<h2>List users from https://reqres.in/api/users?per_page=100</h2>

<table border>
    <thead>
    <tr>
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Avatar</th>
    </tr>
    </thead>
    <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>
                        <img src="${user.avatar}" alt="user avatar">
                    </td>
                </tr>
            </c:forEach>
    </tbody>
</table>

</body>
</html>
