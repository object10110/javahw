package org.samarskii.Servlets;

import org.samarskii.Post;
import org.samarskii.PostRepository;
import org.samarskii.listeners.SessionListener;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/admin", name = "admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("posts", PostRepository.getInstance().getPosts());
        req.setAttribute("sessions", SessionListener.getTotalActiveSession());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String shortText = req.getParameter("short-text");
        String text = req.getParameter("text");
        String author = req.getParameter("author");
        String pathToImage = req.getParameter("path-to-image");

        Post newPost = new Post(PostRepository.getInstance().getPosts().size()+1, title, pathToImage, shortText, text, new Date(), author);

        if (req.getSession().getAttribute("id") == null) {
            req.getSession().setAttribute("id", PostRepository.getInstance().getPosts().size()+1);
        } else {
            int id = Integer.parseInt(req.getSession().getAttribute("id").toString());
            newPost.setId(++id);
            req.getSession().setAttribute("id", id);
        }
        PostRepository.getInstance().save(newPost);
        doGet(req, resp);
    }
}
