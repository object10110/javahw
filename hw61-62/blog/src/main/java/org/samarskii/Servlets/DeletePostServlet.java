package org.samarskii.Servlets;

import org.samarskii.PostRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete", name="delete")
public class DeletePostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
           int id = Integer.parseInt(req.getParameter("id"));
            PostRepository.getInstance().delete(id);
            resp.sendRedirect(getServletContext().getContextPath()+"/admin");
        }
        catch (Exception ex){
            resp.getWriter().write(ex.getMessage());
        }
    }
}
