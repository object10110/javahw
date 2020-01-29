<%@ page import="org.samarskii.Cd" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change CD</title>
</head>
<body>
<h1>Change CD</h1>
<a href="index.jsp">CD list</a>
<%
    ArrayList<Cd> cd = (ArrayList<Cd>) session.getAttribute("cd");
    if (cd != null && cd.size() > 0) {
        for (Cd c : cd) {
%>
<div class="container">
    <form method="post">
        <input type="text" value="<%=c.getName()%>" name="name">
        <input type="text" value="<%=c.getId()%>" name="id" hidden/>
        <input type="submit" value="change"/>
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
            String name = request.getParameter("name");
            if (cds.size() != 0) {
                //cds.removeIf(c-> (c.getId() + "").equals(id))
                int changeCdId = -1;
                for (int i = 0; i < cds.size(); i++) {
                    if(String.valueOf(cds.get(i).getId()).equals(id)){
                        changeCdId = i;
                    }
                }

                if(changeCdId != -1){
                    Cd cd1 = cds.get(changeCdId);
                    cd1.setName(name);
                    session.setAttribute("cd", cds);
                    response.sendRedirect("change.jsp");
                }
            }
        }
    }
%>
</body>
</html>
