<%@ page import="java.util.ArrayList" %>
<%@ page import="org.samarskii.Cd" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.OptionalInt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add CD</title>
</head>
<body>
<h1>Add CD</h1>
<a href="index.jsp">CD list</a>
<form method="post">
    <input type="text" value="cd name" required name="name">
    <input type="submit">
</form>
<%
    if ("POST".equals(request.getMethod())) {
        if (session.isNew() || session.getAttribute("cd") == null) {
            List<Cd> cds = new ArrayList<Cd>();
            session.setAttribute("cd", cds);
        }
        List<Cd> cds = (ArrayList<Cd>) session.getAttribute("cd");
        Cd newCd = new Cd();
        String name = request.getParameter("name");
        if (name != null && name.length() > 0) {
            if (cds.size() == 0) {
                newCd.setId(1);
                session.setAttribute("id", 1);
            } else {
                int id = Integer.parseInt(session.getAttribute("id").toString());
                newCd.setId(++id);
                session.setAttribute("id", id);
            }
            newCd.setName(name);
            cds.add(newCd);
            session.setAttribute("cd", cds);

        }
    }
%>
</body>
</html>
