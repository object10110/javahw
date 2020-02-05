package org.itstep.servlet;

import org.itstep.db.Post;
import org.itstep.db.PostRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@WebServlet(urlPatterns = "/create", name = "create")
public class CreatePostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        String subtitle = req.getParameter("subtitle");
        String author = req.getParameter("author");
        String content = req.getParameter("content");

        PostRepository.getInstance().save(Post.builder()
                .title(title)
                .subTitle(subtitle)
                .author(author)
                .content(content)
                .published(new Date()).build());
        resp.sendRedirect(getServletContext().getContextPath());
    }
}
