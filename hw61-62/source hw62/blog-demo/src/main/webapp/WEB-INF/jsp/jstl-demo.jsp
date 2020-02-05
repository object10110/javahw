<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>

<html>
<head>
    <title></title>
</head>
<body>
<%--<%--%>
<%--    out.print("<ul>");--%>
<%--    for(int i=0; i<10; i++)--%>
<%--    {--%>
<%--        out.print("<li>"+(i + 1) + "item</li>");--%>
<%--    }--%>
<%--    out.print("</ul>");--%>
<%--%>--%>

<ul>
<c:forEach begin="0" end="10" var="i" varStatus="status">
    <c:choose>
        <c:when test="${status.first}">
            <c:set var="style" value="color: red"/>
        </c:when>
        <c:otherwise>
            <c:set var="style" value="color: black"/>
        </c:otherwise>
    </c:choose>

    <li style="${style}">${i} item</li>
</c:forEach>
</ul>

<p>
    Start: ${start}
</p>


<p>
    Application started: ${applicationScope.start}
<%--    <%=application.getAttribute("start")%>--%>
</p>

<p>
    Session started: ${sessionScope.start}
    <%--    <%=application.getAttribute("start")%>--%>
</p>

<p>
    Request started: ${requestScope.start}
    <%--    <%=application.getAttribute("start")%>--%>
</p>

</body>
</html>
