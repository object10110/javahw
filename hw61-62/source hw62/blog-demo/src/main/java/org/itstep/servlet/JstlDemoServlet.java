package org.itstep.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(value = "/jstl", name = "jstl")
public class JstlDemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("start", LocalDateTime.now());
        req.getRequestDispatcher("/WEB-INF/jsp/jstl-demo.jsp").forward(req, resp);
    }
}
