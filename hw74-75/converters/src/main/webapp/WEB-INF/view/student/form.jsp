<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Create Students" />
<%@include file="../include/header.jsp"%>

<div class="container">

    <h1>${title}</h1>

    <div class="row">
        <form:form modelAttribute="student" class="col s12" method="post">
            <form:hidden path="id"/>
            <div class="row">
                <div class="input-field col s6">
                    <form:input path="firstName" required="required" class="validate"/>
                    <form:label path="firstName">First Name</form:label>
                    <form:errors path="firstName" class="helper-text" data-error="wrong" data-success="right"/>
                </div>
                <div class="input-field col s6">
                    <form:input path="lastName" required="required" class="validate"/>
                    <form:label path="lastName">Last Name</form:label>
                    <form:errors path="lastName" class="helper-text" data-error="wrong" data-success="right"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <form:input path="group" required="required" type="text" class="validate autocomplete"/>
                    <form:label path="group">Group</form:label>
                    <form:errors path="group" class="error-text helper-text" data-error="wrong" data-success="right"/>
                    <datalist id="groups">
                        <c:forEach var="group" items="${groups}">
                            <option>${group}</option>
                        </c:forEach>
                    </datalist>
                </div>
                <div class="input-field col s6">
                    <form:input path="birthDate" required="required" type="text" class="validate datepicker"/>
                    <form:label path="birthDate">Age</form:label>
                    <form:errors path="birthDate" class="error-text helper-text" data-error="wrong"
                                 data-success="right"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <div class="form-group">
                        <form:label path="role">Role</form:label>
                        <form:select path="role" cssClass="form-control form-control-sm"><form:options/></form:select>
                        <form:errors path="role" class="error-text helper-text" data-error="wrong"
                                     data-success="right"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <input type="submit" class="btn"/>
                </div>
            </div>
        </form:form>
    </div>

    <jsp:include page="../include/scripts.jsp"/>
</div>

<%@include file="../include/footer.jsp"%>
