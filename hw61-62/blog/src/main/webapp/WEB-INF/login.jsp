<%@ page import="org.samarskii.Post" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Blog Post - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="static/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="static/css/blog-post.css" rel="stylesheet">

</head>

<body>
<!-- Navigation -->
<%@include file="includes/navigation.jsp" %>

<!-- Page Content -->
<div class="container">

    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <br>
            <form method="post">
                <div class="form-group">
                    <label for="exampleInputEmail1">Your login</label>
                    <input type="text" name="login" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter login">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Your password</label>
                    <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
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
