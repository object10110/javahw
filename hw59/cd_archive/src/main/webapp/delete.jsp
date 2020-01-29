<%@ page import="org.samarskii.Cd" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<h1>Delete CD</h1>
<a href="index.jsp">CD list</a>
<%
    ArrayList<Cd> cd = (ArrayList<Cd>) session.getAttribute("cd");
    if (cd != null && cd.size() > 0) {
        for (Cd c : cd) {
%>
<div class="container">
    <h2><%=c.getName()%></h2>
    <form method="post">
        <input type="text" value="<%=c.getId()%>" name="id" hidden/>
        <input type="submit" value="delete"/>
    </form>
</div>
<%
        }
    }
%>

<%
    if("POST".equals(request.getMethod())) {
        if (session.getAttribute("cd") != null) {
            List<Cd> cds = (ArrayList<Cd>) session.getAttribute("cd");

            String id = request.getParameter("id");

            if (cds.size() != 0) {
                //cds.removeIf(c-> (c.getId() + "").equals(id))
                int deleteCdId = -1;
                for (int i = 0; i < cds.size(); i++) {
                    if(String.valueOf(cds.get(i).getId()).equals(id)){
                        deleteCdId = i;
                    }
                }

                if(deleteCdId != -1){
                    cds.remove(deleteCdId);
                    session.setAttribute("cd", cds);
                    response.sendRedirect("delete.jsp");
                }
            }
        }
    }
%>
</body>
</html>
