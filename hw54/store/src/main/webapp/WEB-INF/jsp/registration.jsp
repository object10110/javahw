<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Registration</title>
    <link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet"/>
</head>
<body>

<div class="container">
    <div class="panel panel-login">
        <form action="<%=request.getContextPath()%>/registration" method="post">
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