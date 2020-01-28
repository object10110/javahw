package org.itstep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class SecretServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean exists = Arrays.stream(request.getCookies()).anyMatch(c -> "user_id".equals(c.getName()));
        if (exists) {
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
        } else {
            response.sendRedirect(getServletContext().getContextPath() + "/");
        }
    }
}
