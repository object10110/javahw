<%@ page import="java.util.ArrayList" %>
<%@ page import="org.samarskii.Cd" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CD list</title>
</head>
<body>
<h1>CD list</h1>
<a href="add.jsp">create CD</a>
<a href="delete.jsp">delete CD</a>
<a href="change.jsp">change CD</a>
<%
    ArrayList<Cd> cd = (ArrayList<Cd>)session.getAttribute("cd");
    if(cd!= null && cd.size()>0){
        for (Cd c:cd) {
%>
    <h2><%=c.getName()%></h2>
<%
        }
    }
%>
</body>
</html>
