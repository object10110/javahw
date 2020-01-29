<%@ page import="java.util.List" %>
<%@ page import="org.itstep.Student" %><%--
  Created by IntelliJ IDEA.
  User: shaptala
  Date: 22.01.2020
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Age</th>
    </tr>
    </thead>
    <tbody>
    <%
        if(session.getAttribute("students")!=null) {
            List<Student> students = (List<Student>)session.getAttribute("students");
            for (Student student: students) {
                out.write("<tr>");
                out.write("<td>" + student.getId() + "</td>");
                out.write("<td>" + student.getName() + "</td>");
                out.write("<td>" + student.getAge() + "</td>");
                out.write("</tr>");
            }
        }
    %>
    </tbody>
</table>
</body>
</html>
