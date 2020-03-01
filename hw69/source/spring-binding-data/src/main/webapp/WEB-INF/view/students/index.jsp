<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
</head>
<body>
<h1>Students</h1>
<p><a href="<spring:url value="/students/new"/>">Create student</a></p>

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
    <%--        <c:forEach var="student" items="${students}">--%>
    <%--            <tr>--%>
    <%--                <td>${student.id}</td>--%>
    <%--                <td>${student.firstName}</td>--%>
    <%--                <td>${student.lastName}</td>--%>
    <%--                <td>${student.age}</td>--%>
    <%--                <td>${student.group}</td>--%>
    <%--                <td><a href="<spring:url value="/students/delete/${student.id}"/>">Delete</a>--%>
    <%--                    <br/>--%>
    <%--                    <a href="<spring:url value="/students/update/${student.id}"/>">Update</a> </td>--%>
    <%--            </tr>--%>
    <%--        </c:forEach>--%>
    </tbody>
</table>
<script>
    function loadData() {
        let tbody = document.querySelector("tbody");
        tbody.innerHTML = "";
        var xhr = new XMLHttpRequest();
        xhr.open("GET", '<spring:url value="/api/students"/>', true);
        xhr.onreadystatechange = function () {
            console.log(xhr.readyState);
            if (xhr.readyState == 4 && xhr.status == 200) {
                console.log(xhr.getResponseHeader("Content-Type"));
                console.log(xhr.responseText); // string
                var students = JSON.parse(xhr.responseText);
                console.log(students);

                students.forEach((s) => {
                    let tr = document.createElement("tr");
                    for (let val in s) {
                        let td = document.createElement("td");
                        td.appendChild(document.createTextNode(s[val]));
                        tr.appendChild(td);
                    }
                    let td = document.createElement("td");
                    let btn = document.createElement("button");
                    btn.setAttribute('data-id', s.id);
                    btn.appendChild(document.createTextNode('Delete'));

                    addDeleteHandler(btn);

                    td.appendChild(btn);
                    tr.appendChild(td);
                    tbody.appendChild(tr);
                });
            }
        };

        xhr.send();
    }

    loadData();

    function addDeleteHandler(btn) {
        btn.addEventListener("click", function () {
            let id = this.getAttribute('data-id');
            console.log("DELETE: " + id);
            // TODO: send to service DELETE Request.
            // EndPoint: /api/students/{id}
            // Method: DELETE

            let url = "<spring:url value="/api/students/"/>" + id;
            fetch(url, {method: 'DELETE'})
                .then(resp => {
                    console.log("Ok");
                    let tr = btn.parentElement.parentElement;
                    let tbody = tr.parentElement;
                    tbody.removeChild(tr);
                }).catch(error => {
                    console.error("Error remove: " + error);
                });
        });
    }

</script>

<h2>Create students</h2>
<form method="post" action="<spring:url value="/students/new"/>">
    <div>
        <label for="firstName">First name: </label>
        <input name="firstName" id="firstName" required/>
    </div>
    <div>
        <label for="lastName">Last name: </label>
        <input name="lastName" id="lastName" required/>
    </div>
    <div>
        <label for="age">Age: </label>
        <input name="age" id="age" type="number" min="8" max="60" required/>
    </div>

    <div>
        <label for="group">Group: </label>
        <input name="group" id="group" required/>
    </div>

    <div>
        <input type="submit"/>
    </div>
</form>
<div>${message}</div>
</body>
</html>
