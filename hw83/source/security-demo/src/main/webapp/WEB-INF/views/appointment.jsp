<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Kevin's Auto Service Center</title>

    <%@include file="include/head.jsp" %>

    <link rel="stylesheet" href="<spring:url value="/resources/css/global.css"/>"/>
    <link rel="stylesheet" href="<spring:url value="/resources/css/datepicker.css"/>"/>
    <link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-multiselect.css"/>"/>

    <script src="<spring:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
    <script src="<spring:url value="/resources/js/bootstrap-multiselect.js"/>"></script>
    <script src="<spring:url value="/resources/js/appointments.js"/>"></script>
    <script>
        var root = "${pageContext.request.contextPath}";
    </script>
</head>
<body>
<jsp:include page="include/menu.jsp"/>
<div class="container">
    <div class="row">
        <h1>Appointment</h1>
    </div>
    <ul class="list-group">
        <li class="list-group-item">
            <label>Customer:</label><span>${appointment.user.firstName } ${appointment.user.lastName}</span></li>
        <li class="list-group-item"><label>Appointment Date:</label><span>${appointment.appointmentDt}</span></li>
        <li class="list-group-item"><label>Make:</label><span>${appointment.automobile.make}</span></li>
        <li class="list-group-item"><label>Model:</label><span>${appointment.automobile.model }</span></li>
        <li class="list-group-item"><label>Year:</label><span>${appointment.automobile.year }</span></li>
        <li class="list-group-item">
            <label>Services:</label>
            <ul>
                <c:forEach items="${appointment.services}" var="service">
                    <li>${service}</li>
                </c:forEach>
            </ul>
        </li>
        <li class="list-group-item"><label>Status:</label><span id="status">${appointment.status}</span>
        <li class="list-group-item">
            <a class="btn btn-default" href="<spring:url value="/appointments/"/>" role="button">Back</a>
            <a class="btn btn-default update"
               href="<spring:url value="/appointments/${appointment.appointmentId}/cancel"/>"
               role="button">Cancel</a>
            <a class="btn btn-default update"
               href="<spring:url value="/appointments/${appointment.appointmentId}/confirm"/>"
               role="button">Confirm</a>
            <a class="btn btn-default update"
               href="<spring:url value="/appointments/${appointment.appointmentId}/complete"/>"
               role="button">Mark
                Complete</a>
        </li>
        <script>
            $(".update").click((e) => {
                e.preventDefault();
                $.get($(e.target).attr('href'))
                    .done((result) => {
                        console.log(result);
                        $("#status")
                            .fadeOut("slow", function () {
                                $(this).text(result)
                            })
                            .fadeIn("slow");
                    })
                    .fail((err) => {
                        console.error(err);
                    });
            });
        </script>
    </ul>
</div>

</body>
</html>