package org.itstep.servlet;

import org.itstep.db.PostRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/", name = "home", initParams = {
        @WebInitParam(name = "user", value = "admin")
}, loadOnStartup = 1)
public class HomeServlet extends HttpServlet {
    String user;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        user = config.getInitParameter("user"); // admin
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet Home Servlet");
        req.setAttribute("user", user);
        req.setAttribute("posts", PostRepository.getInstance().getPosts());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
