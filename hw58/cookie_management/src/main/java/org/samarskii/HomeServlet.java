package org.samarskii;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");
        String value = req.getParameter("value");
        String parameterAge = req.getParameter("max-age");
        int maxAge = !parameterAge.equals("") ? Integer.parseInt(parameterAge) : -2;
        String domain = req.getParameter("domain");
        boolean httpOnly = req.getParameter("http-only") != null;
        boolean secure = req.getParameter("secure") != null;

        Cookie newCookie = new Cookie(key, value);
        if (maxAge != -2) {
            newCookie.setMaxAge(maxAge);
        }
        if (!domain.equals("")) {
            newCookie.setDomain(domain);
        }
        if (httpOnly) {
            newCookie.setHttpOnly(true);
        }
        if (secure) {
            newCookie.setSecure(true);
        }

        resp.addCookie(newCookie);
        resp.sendRedirect(req.getContextPath() + "/");
        //req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
