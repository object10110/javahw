<!DOCTYPE html>
<html lang="en">

<head>

  <%@include file="include/header.jsp"%>

</head>

<body>

  <!-- Navigation -->
  <%@include file="include/navigation.jsp"%>

  <!-- Page Header -->
  <header class="masthead" style="background-image: url('static/img/post-bg.jpg')">
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="post-heading">
            <h1>Man must explore, and this is exploration at its greatest</h1>
            <h2 class="subheading">Problems look mighty small from 150 miles up</h2>
            <span class="meta">Posted by
              <a href="#">Start Bootstrap</a>
              on August 24, 2019</span>
          </div>
        </div>
      </div>
    </div>
  </header>

  <!-- Post Content -->
  <article>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <form method="post">
            <label>
              Title <input name="title" />
            </label>
            <br/>
            <label>
              Subtitle <input name="subtitle" />
            </label>
            <br/>
            <label>
              Author <input name="author" />
            </label>
            <br/>
            <label>
              Content<br/> <textarea name="content"></textarea>
            </label>
            <br/>
            <input type="submit" />
          </form>
        </div>
      </div>
    </div>
  </article>

  <hr>

  <!-- Footer -->
  <%@include file="include/footer.jsp"%>

  <!-- Bootstrap core JavaScript -->
  <script src="static/vendor/jquery/jquery.min.js"></script>
  <script src="static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Custom scripts for this template -->
  <script src="static/js/clean-blog.min.js"></script>

</body>

</html>
