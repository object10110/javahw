<html lang="en">
<head>
    <title>Registration</title>
    <link href="<%=request.getContextPath()%>/static/css/style.css" rel="stylesheet"/>
</head>
<body>
<!--<form action="<%=request.getContextPath()%>/" method="post">
    <div>
        <label for="login">Login</label>
        <input name="login" id="login"/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password"/>
    </div>
    <input type="submit"/>
</form>
-->
<div class="container">
    <div class="panel panel-login">
        <form action="<%=request.getContextPath()%>/" method="post">
            <div class="input-container">
                <label for="login">Enter login</label>
                <input type="text" id="login" name="login">
            </div>
            <div class="input-container">
                <label for="password">Enter password</label>
                <input type="password" id="password" name="password">
            </div>
            <div class="submit-container">
                <input type="submit" value="Registration">
            </div>
        </form>
    </div>
</div>
</body>
</html>