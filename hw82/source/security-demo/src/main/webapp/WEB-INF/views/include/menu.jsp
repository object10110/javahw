<%@page isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a href="<spring:url value="/"/>" class="navbar-brand">Kevin's Auto Service Center</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="<spring:url value="/services"/>">Services</a></li>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
                <li><a href="<spring:url value="/schedule"/>">Schedule</a></li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="<spring:url value="/appointments"/>">Appointments</a></li>
                <li><a href="<spring:url value="/h2console"/>" target="_blank">H2 console</a></li>
            </sec:authorize>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <sec:authorize access="isAuthenticated()" var="authenticated"/>
            <c:choose>
                <c:when test="${authenticated}">
                    <li>
                        <p class="navbar-text">
                            Welcome <sec:authentication property="name"/>
                            <a id="logout" href="<spring:url value="/logout"/>">Sing Out</a>
                        </p>
                    </li>
                </c:when>
                <c:otherwise>
                    <li><a href="<spring:url value="/login"/>">Sign In</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
<form method="post" id="logout-form" action="<spring:url value="/logout"/>">
    <sec:csrfInput/>
</form>

<script>
    const logoutLink = document.getElementById("logout");
    logoutLink.addEventListener("click", (e) => {
        e.preventDefault();
        const logoutForm = document.getElementById("logout-form");
        logoutForm.submit();
    });
</script>