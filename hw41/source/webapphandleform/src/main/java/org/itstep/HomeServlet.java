package org.itstep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // получение данных отправленных пользователем
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Enumeration<String> parameterNames = req.getParameterNames();
        while(parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String[] values = req.getParameterValues(name);
            System.out.println(name + " = " + String.join(", " , values));
        }


        // формирование ответа пользователю
        PrintWriter writer = resp.getWriter();
        writer.println("Hello " + login + " Your password: " + password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
