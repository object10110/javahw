package org.samarskii.Servlets;

import org.samarskii.Post;
import org.samarskii.PostRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/change")
public class ChangePostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String title = req.getParameter("title");
            String shortText = req.getParameter("short-text");
            String text = req.getParameter("text");
            String author = req.getParameter("author");
            String pathToImage = req.getParameter("path-to-image");
            int id = Integer.parseInt(req.getParameter("id"));

            Post newPost = new Post(id, title, pathToImage, shortText, text, new Date(), author);

            PostRepository.getInstance().change(newPost);
        }
        catch (Exception ex){}
        resp.sendRedirect(getServletContext().getContextPath()+"/admin");
    }
}
