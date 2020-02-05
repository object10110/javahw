<%@ page import="java.util.List" %>
<%@ page import="org.itstep.db.Post" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="include/header.jsp" %>
</head>

<body>

<!-- Navigation -->
<%@include file="include/navigation.jsp" %>

<!-- Page Header -->
<header class="masthead" style="background-image: url('static/img/home-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="site-heading">
                    <h1>Clean Blog</h1>
                    <span class="subheading">A Blog Theme by Start Bootstrap</span>
                </div>
            </div>
        </div>
    </div>
</header>
<% List<Post> posts = (List<Post>) request.getAttribute("posts"); %>
<!-- Main Content -->
<div class="container">

    <div class="row">

        <div class="col-lg-8 col-md-10 mx-auto">

            <% for (Post post : posts) { %>
            <div class="post-preview">
                <a href="post.jsp">
                    <h2 class="post-title">
                        <%--              Man must explore, and this is exploration at its greatest--%>
                        <%=post.getTitle()%>
                    </h2>
                    <%if (post.getSubTitle() != null) {%>
                    <h3 class="post-subtitle">
                        <%--              Problems look mighty small from 150 miles up--%>
                        <%=post.getSubTitle()%>
                    </h3>
                    <%}%>
                </a>
                <p class="post-meta">Posted by
                    <a href="#"><%=post.getAuthor()%>
                    </a>
                    on <%=post.getPublished()%>
                </p>
            </div>
            <hr>
            <% } %>
            <%--        <div class="post-preview">--%>
            <%--          <a href="post.jsp">--%>
            <%--            <h2 class="post-title">--%>
            <%--              I believe every human has a finite number of heartbeats. I don't intend to waste any of mine.--%>
            <%--            </h2>--%>
            <%--          </a>--%>
            <%--          <p class="post-meta">Posted by--%>
            <%--            <a href="#">Start Bootstrap</a>--%>
            <%--            on September 18, 2019</p>--%>
            <%--        </div>--%>
            <%--        <hr>--%>
            <%--        <div class="post-preview">--%>
            <%--          <a href="post.jsp">--%>
            <%--            <h2 class="post-title">--%>
            <%--              Science has not yet mastered prophecy--%>
            <%--            </h2>--%>
            <%--            <h3 class="post-subtitle">--%>
            <%--              We predict too much for the next year and yet far too little for the next ten.--%>
            <%--            </h3>--%>
            <%--          </a>--%>
            <%--          <p class="post-meta">Posted by--%>
            <%--            <a href="#">Start Bootstrap</a>--%>
            <%--            on August 24, 2019</p>--%>
            <%--        </div>--%>
            <%--        <hr>--%>
            <%--        <div class="post-preview">--%>
            <%--          <a href="post.jsp">--%>
            <%--            <h2 class="post-title">--%>
            <%--              Failure is not an option--%>
            <%--            </h2>--%>
            <%--            <h3 class="post-subtitle">--%>
            <%--              Many say exploration is part of our destiny, but it’s actually our duty to future generations.--%>
            <%--            </h3>--%>
            <%--          </a>--%>
            <%--          <p class="post-meta">Posted by--%>
            <%--            <a href="#">Start Bootstrap</a>--%>
            <%--            on July 8, 2019</p>--%>
            <%--        </div>--%>
            <%--        <hr>--%>
            <!-- Pager -->
            <div class="clearfix">
                <a class="btn btn-primary float-right" href="#">Older Posts &rarr;</a>
            </div>
        </div>
    </div>
</div>
<hr>
<!-- Footer -->
<%@include file="include/footer.jsp" %>

<!-- Bootstrap core JavaScript -->
<script src="static/vendor/jquery/jquery.min.js"></script>
<script src="static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Custom scripts for this template -->
<script src="static/js/clean-blog.min.js"></script>

</body>

</html>
