package org.samarskii;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/change")
public class ChangeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldKey = req.getParameter("old-key");
        String key = req.getParameter("key");
        String value = req.getParameter("value");
        String parameterAge = req.getParameter("max-age");
        int maxAge = !parameterAge.equals("") ? Integer.parseInt(parameterAge) : -2;
        String domain = req.getParameter("domain");
        boolean httpOnly = req.getParameter("http-only") != null;
        boolean secure = req.getParameter("secure") != null;

        Cookie[] cookies = req.getCookies();

        Cookie cookie = null;
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(oldKey.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }
        if(cookie != null){
            if(!key.equals(oldKey)){
                //если сменили ключ то удаляем старую куку
                Cookie oldCookie = new Cookie(oldKey, "");
                oldCookie.setMaxAge(0);
                resp.addCookie(oldCookie);
                cookie = new Cookie(key, value);
            }
            cookie.setValue(value);
            if (maxAge > -2) {
                cookie.setMaxAge(maxAge);
            }
            if (!domain.equals("")) {
                cookie.setDomain(domain);
            }
            if (cookie.isHttpOnly() != httpOnly) {
                cookie.setHttpOnly(httpOnly);
            }
            if (cookie.getSecure() != secure) {
                cookie.setSecure(secure);
            }

            resp.addCookie(cookie);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
