<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>${title}</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <link href="<spring:url value="/static/css/style.css"/>" rel="stylesheet"/>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>

<nav>
    <div class="nav-wrapper">
        <a href="<spring:url value="/"/>" class="brand-logo"><i class="material-icons">school</i> Academy</a>
        <a href="#" data-target="mobile-nav" class="sidenav-trigger"><i class="material-icons">menu</i></a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <security:authorize access="isAuthenticated()" var="authenticated"/>
            <li><a href="<spring:url value="/"/>">Home</a></li>

            <!-- Modal Trigger -->
            <c:choose>
                <c:when test="${authenticated}">
                    <!-- Dropdown Trigger -->
                    <li><a class="dropdown-trigger" href="#!" data-target="dropdown-students">Students<i
                            class="material-icons right">arrow_drop_down</i></a></li>
                    <li><a class="dropdown-trigger" href="#!" data-target="dropdown-groups">Groups<i
                            class="material-icons right">arrow_drop_down</i></a></li>
                    <li><a class="dropdown-trigger" href="#!" data-target="dropdown-teachers">Teachers<i
                            class="material-icons right">arrow_drop_down</i></a></li>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li><a href="<spring:url value="/h2console"/>" target="_blank">H2 Console</a></li>
                    </security:authorize>
                    <li><a class="waves-effect waves-light modal-trigger" id="logout" href="/logout">Log Out</a></li>
                </c:when>
                <c:otherwise>
                    <li><a class="waves-effect waves-light modal-trigger" href="#modalRegister">Register</a></li>
                    <li><a class="waves-effect waves-light modal-trigger" href="#modal1">Sing in</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
    <ul class="sidenav" id="mobile-nav">
        <li><a href="<spring:url value="/"/>">Home</a></li>
        <!-- Dropdown Trigger -->
        <li><a class="dropdown-trigger-mobile" href="#!" data-target="dropdown-students-mobile">Students<i
                class="material-icons right">arrow_drop_down</i></a></li>
    </ul>
    <ul id="dropdown-students" class="dropdown-content">
        <li><a href="<spring:url value="/students"/>"><i class="material-icons">person</i>List</a></li>
        <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
            <li><a href="<spring:url value="/students/create"/>"><i class="material-icons">person_add</i>Add</a></li>
        </security:authorize>
    </ul>
    <ul id="dropdown-students-mobile" class="dropdown-content">
        <li><a href="<spring:url value="/students"/>"><i class="material-icons">person</i>List</a></li>
        <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
            <li><a href="<spring:url value="/students/create"/>"><i class="material-icons">person_add</i>Add</a></li>
        </security:authorize>
    </ul>

    <ul id="dropdown-groups" class="dropdown-content">
        <li><a href="<spring:url value="/groups"/>"><i class="material-icons">group</i>List</a></li>
        <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">
            <li><a href="<spring:url value="/groups/create"/>"><i class="material-icons">group_add</i>Add</a></li>
        </security:authorize>
    </ul>

    <ul id="dropdown-teachers" class="dropdown-content">
        <li><a href="<spring:url value="/teachers"/>"><i class="material-icons">group</i>List</a></li>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="<spring:url value="/teachers/create"/>"><i class="material-icons">person_add</i>Add</a></li>
        </security:authorize>
    </ul>

</nav>

<!-- Modal Structure -->
<div id="modal1" class="modal">
    <form action="/login" method="post">
        <div class="modal-content">
            <h4>Sing in</h4>
            <div>
                <label>Username<input name="username" required/></label>
            </div>
            <div>
                <label>Password<input name="password" required type="password"/></label>
            </div>
            <div>
                <input style="width: 20px; position: initial; opacity: 1; display: inline; pointer-events: all" type="checkbox" name="remember-me" id="remember-me"/>
                <label style="display: inline;" for="remember-me">Remember me</label>
            </div>
        </div>

        <div class="modal-footer">
            <a type="submit" href="#!" class="modal-close waves-effect waves-green btn">Cancel</a>
            <button type="submit" class="waves-effect waves-green btn">Login</button>
        </div>
        <security:csrfInput/>
    </form>

</div>

<!-- Modal Structure -->
<div id="modalRegister" class="modal">
    <form action="/register" method="post">
        <div class="modal-content">
            <h4>Register</h4>
            <div>
                <label>First name<input name="firstName" required/></label>
            </div>
            <div>
                <label>Last name<input name="lastName" required/></label>
            </div>
            <div>
                <div class="modal datepicker-modal" id="modal-4152e483-376f-0816-67c0-756ff80fabd9" tabindex="0">
                    <div class="modal-content datepicker-container">
                        <div class="datepicker-date-display"><span class="year-text"></span><span
                                class="date-text"></span></div>
                        <div class="datepicker-calendar-container">
                            <div class="datepicker-calendar"></div>
                            <div class="datepicker-footer">
                                <button class="btn-flat datepicker-clear waves-effect" style="visibility: hidden;"
                                        type="button"></button>
                                <div class="confirmation-btns">
                                    <button class="btn-flat datepicker-cancel waves-effect" type="button">Cancel
                                    </button>
                                    <button class="btn-flat datepicker-done waves-effect" type="button">Ok</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <label for="birthDate">Birth date</label>
                <input id="birthDate" name="birthDate" type="text" class="validate datepicker" required="required"
                       value="">
            </div>
            <div>
                <label>Username<input name="username" required/></label>
            </div>
            <div>
                <label>Password<input name="password" required type="password"/></label>
            </div>
        </div>
        <div class="modal-footer">
            <a type="submit" href="#!" class="modal-close waves-effect waves-green btn">Cancel</a>
            <button type="submit" class="waves-effect waves-green btn">Register</button>
        </div>
        <security:csrfInput/>
    </form>

</div>

<form method="post" id="logout-form" action="<spring:url value="/logout"/>">
    <security:csrfInput/>
</form>

<script>
    const logoutLink = document.getElementById("logout");
    logoutLink.addEventListener("click", (e) => {
        e.preventDefault();
        const logoutForm = document.getElementById("logout-form");
        logoutForm.submit();
    });
</script>