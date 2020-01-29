package org.itstep;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/setcount")
public class DemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer count;
        if(session.isNew())
        {
            count = 1;
        }
        else
        {
            if(session.getAttribute("count")!=null) {
                count = (Integer) session.getAttribute("count");
                count++;
            } else {
                count = 1;
            }
        }
        session.setAttribute("count", count);

        resp.sendRedirect(getServletContext().getContextPath() + "/");
    }
}
