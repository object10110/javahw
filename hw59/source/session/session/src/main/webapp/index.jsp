<%--директивы--%>
<%@page contentType="text/html" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Hello JSP</title>
</head>
<body>

<h2>Hello World!</h2>

<%--<jsp:include page="header.jsp"></jsp:include>--%>
<%@include file="header.jsp"%>
<p>SessionID: <%=session.getId()%></p>
<p>Timeout: <%=session.getMaxInactiveInterval()/60%></p>
<p>Count: <%=session.getAttribute("count")%></p>

<%! // объявление полей класса
    int a = 10;
%>

<!-- пароль: 1234 -->
<%-- серверный коментарий --%>
<p>this.a = <%= this.a %></p>

<%
    int a = 10; // локальная переменная
    out.println("a = " + a + "<br/>");
    a++;
    this.a++;
    //HttpSession httpSession = request.getSession();
%>

<% // скриплеты
    for(int i=0; i<10; i++) {
        out.println("Hello JSP<br/>");
    }
%>

<a href="/setcount">Set count</a>

</body>
</html>
