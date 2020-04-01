<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="title" value="Create Students"/>
<%@include file="../include/header.jsp" %>

<div class="container">

    <h1>${title}</h1>

    <div class="row">
        <form:form modelAttribute="teacherDto" class="col s12" method="post">
            <form:hidden path="id"/>
            <div class="row">
                <div class="input-field col s6">
                    <form:input path="firstName" required="required" class="validate"/>
                    <form:label path="firstName">First name</form:label>
                    <form:errors path="firstName" class="helper-text" data-error="wrong" data-success="right"/>
                </div>
                <div class="input-field col s6">
                    <form:input path="lastName" required="required" class="validate"/>
                    <form:label path="lastName">Last name</form:label>
                    <form:errors path="lastName" class="helper-text" data-error="wrong" data-success="right"/>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s6">
                    <select multiple="multiple" name="groupsId" id="groupsId">
                        <c:forEach items="${groupsDto}" var="g">
                            <option value="${g.id}">${g.name}</option>
                        </c:forEach>
                    </select>
                    <label for="groupsId">Select groups</label>
<%--                    <form:select path="groupsId">--%>
<%--                        <form:option value="" disabled="true" selected="true">Choose your option</form:option>--%>
<%--                        <form:options items="${groupsDto}" itemLabel="name" itemValue="id"/>--%>
<%--                    </form:select>--%>
<%--                    <label>Group</label>--%>
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

<%@include file="../include/footer.jsp" %>
