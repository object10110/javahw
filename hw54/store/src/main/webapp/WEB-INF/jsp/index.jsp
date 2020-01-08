<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet"/>
</head>
<body>

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
                <input type="submit" value="LogIn">
            </div>
            <div class="registration-container">
                <label>Not registered?</label>
                <a class="create-account" href="<%=request.getContextPath()%>/registration">create account</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>