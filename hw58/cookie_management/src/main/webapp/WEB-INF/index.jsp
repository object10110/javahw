<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/style.css">
</head>
<body>

<div class="container">

    <div class="create">
        <h2>Create cookie</h2>
        <form action="<%=request.getContextPath()%>/" method="post">
            <div class="params">
                <div class="param">
                    <label>Key</label>
                    <input type="text" name="key" placeholder="key">
                </div>
                <div class="param">
                    <label>Value</label>
                    <input type="text" name="value" placeholder="value">
                </div>
                <div class="param">
                    <label>Max age</label>
                    <input type="number" name="max-age" placeholder="max age">
                </div>
                <div class="param">
                    <label>Domain</label>
                    <input type="text" name="domain" placeholder="domain">
                </div>
                <div class="param">
                    <label>Http only</label>
                    <input type="checkbox" name="http-only">
                </div>
                <div class="param">
                    <label>Secure</label>
                    <input type="checkbox" name="secure">
                </div>
                <div class="param">
                    <input type="submit" value="create">
                </div>
            </div>
        </form>
    </div>

    <div class="change">
        <h2>Change cookie</h2>

        <% Cookie[] cookies = request.getCookies();
        if(cookies!= null){
            for (Cookie c :cookies) {%>
        <div class="change-item">
            <form action="<%=request.getContextPath()%>/change" method="post">
                <div class="params">
                    <input type="text" name="old-key" value="<%=c.getName()%>" hidden/>
                    <div class="param">
                        <label>Key</label>
                        <input type="text" name="key" placeholder="key" value="<%=c.getName()%>"/>
                    </div>
                    <div class="param">
                        <label>Value</label>
                        <input type="text" name="value" placeholder="value" value="<%=c.getValue()%>">
                    </div>
                    <div class="param">
                        <label>Max age</label>
                        <input type="text" name="max-age"  placeholder="max age" value="<%=c.getMaxAge()%>">
                    </div>
                    <div class="param">
                        <label>Domain</label>
                        <input type="text" name="domain" placeholder="domain" value="<%=c.getDomain()!= null?c.getDomain():""%>">
                    </div>
                    <div class="param">
                        <label>Http only</label>
                        <input type="checkbox" name="http-only" <%=c.isHttpOnly()?"checked":""%>>
                    </div>
                    <div class="param">
                        <label>Secure</label>
                        <input type="checkbox" name="secure" <%= c.getSecure()? "checked":""%>>
                    </div>
                    <div class="param">
                        <input type="submit" value="change">
                    </div>
                </div>
            </form>
            <form action="<%=request.getContextPath()%>/delete" method="post">
                <div class="param">
                    <input type="text" name="key" value="<%=c.getName()%>" hidden/>
                    <input type="submit" value="delete">
                </div>
            </form>
        </div>
    <%}
        } %>
    </div>

</div>

</body>
</html>
