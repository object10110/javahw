<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.samarskii.Post" %>
<%@ page import="java.util.List" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog Home - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="static/css/blog-home.css" rel="stylesheet">

</head>

<body>
<!-- Navigation -->
<%@include file="includes/navigation.jsp" %>

<!-- Page Content -->
<div class="container">

    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <br>
            <h1>Active sessions: ${sessions}</h1>
            <br>
            <h1>CREATE POST</h1>
            <div class="card">
                <div class="card-body">
                    <form method="post">
                        <div class="form-group">
                            <label>Title</label>
                            <input type="text" required name="title" class="form-control" placeholder="Enter Title">
                        </div>
                        <div class="form-group">
                            <label>Short text</label>
                            <input type="text" required name="short-text" class="form-control"
                                   placeholder="Enter Short text">
                        </div>
                        <div class="form-group">
                            <label>Text</label>
                            <textarea rows="5" required name="text" class="form-control"
                                      placeholder="Enter Text"></textarea>
                        </div>
                        <div class="form-group">
                            <label>Path to image</label>
                            <input type="text" name="path-to-image" class="form-control"
                                   placeholder="Enter Path to image"/>
                        </div>
                        <div class="form-group">
                            <label>Author</label>
                            <input type="text" required name="author" class="form-control" placeholder="Enter Author">
                        </div>
                        <button type="submit" class="btn btn-primary">Create post</button>
                    </form>
                </div>
            </div>
            <br>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <br>
            <h1>CHANGE POSTS</h1>
            <%--@elvariable id="posts" type="java.util.List<org.samarskii.Post>"--%>
            <c:forEach items="${posts}" var="post">
                <div class="card">
                    <div class="card-body">
                        <form method="post" action="${context}/change">
                            <input name="id" value="${post.id}" hidden>
                            <div class="form-group">
                                <label>Title</label>
                                <input type="text" value="${post.title}" required name="title" class="form-control"
                                       placeholder="Enter Title">
                            </div>
                            <div class="form-group">
                                <label>Short text</label>
                                <input type="text" value="${post.shortText}" required name="short-text"
                                       class="form-control"
                                       placeholder="Enter Short text">
                            </div>
                            <div class="form-group">
                                <label>Text</label>
                                <textarea rows="5" required name="text" class="form-control"
                                          placeholder="Enter Text">${post.text}</textarea>
                            </div>
                            <div class="form-group">
                                <label>Path to image</label>
                                <input type="text" value="${post.pathToImg}" name="path-to-image" class="form-control"
                                       placeholder="Enter Path to image"/>
                            </div>
                            <div class="form-group">
                                <label>Author</label>
                                <input type="text" value="${post.author}" required name="author" class="form-control"
                                       placeholder="Enter Author">
                            </div>
                            <button type="submit" class="btn btn-primary">Change post</button>
                        </form>
                        <br>
                        <form action="${context}/delete" method="post">
                            <input name="id" value="${post.id}" hidden>
                            <button type="submit" class="btn btn-primary">DELETE this post</button>
                        </form>
                    </div>
                </div>
                <br>
            </c:forEach>

            <br>
        </div>
    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<!-- Footer -->
<%@include file="includes/footer.jsp" %>

<!-- Bootstrap core JavaScript -->
<script src="static/vendor/jquery/jquery.min.js"></script>
<script src="static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
