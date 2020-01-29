<%@ page import="org.itstep.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<html>
<body>
<h2>Hello World!</h2>
<form method="post">
    <fieldset>
        <legend>Student form</legend>
        <label>
            Name <input name="name" required/>
        </label>
        <br/>
        <label>
            Age <input name="age" min="0" max="100" required/>
        </label>
        <br/>
    </fieldset>
    <input type="submit"/>
</form>
<p>Method: <%= request.getMethod() %> </p>
<p><a href="students.jsp">All students</a> </p>
<%
    if(session.isNew() || session.getAttribute("students")==null) {
        session.setAttribute("students", new ArrayList<Student>());
        out.println("Created list");
    }
    if("POST".equals(request.getMethod())) {
        List<Student> students = (List<Student>)session.getAttribute("students");
        Student student = new Student(students.size() + 1, request.getParameter("name"), Integer.parseInt(request.getParameter("age")));
        students.add(student);
    }
%>
</body>
</html>
