<html lang="en">
<head>
    <title>Registration</title>
    <link href="<%=request.getContextPath()%>/static/css/style.css" rel="stylesheet"/>
</head>
<body>
<form action="<%=request.getContextPath()%>/" method="post">
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
</body>
</html>
