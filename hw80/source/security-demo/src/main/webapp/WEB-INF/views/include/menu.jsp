<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a href="<spring:url value="/"/>" class="navbar-brand">Kevin's Auto Service Center</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="<spring:url value="/services"/>">Services</a></li>
            <li><a href="<spring:url value="/appointments"/>">Appointments</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="<spring:url value="/h2console"/>" target="_blank">H2 console</a></li>
            <li><a href="<spring:url value="/login"/>">Sign In</a></li>
            <li><a id="logout" href="<spring:url value="/logout"/>">Sing Out</a></li>

        </ul>
    </div>
</nav>
<form id="form-logout" method="post" action="<spring:url value="/logout"/>">
    <sec:csrfInput/>
</form>
<script>
    $("#logout").click((e) => {
        e.preventDefault();
        $("#form-logout").submit();
    })
</script>