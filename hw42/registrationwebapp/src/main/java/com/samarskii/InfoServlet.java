package com.samarskii;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class InfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = "admin", password = "admin";
        String log = req.getParameter("login");
        String pass = req.getParameter("password");
        if(login.equals(log)
            && password.equals(pass))
        {
            Gson gson = new Gson();
            ArrayList<User> list = new ArrayList<>();
            Save save;
            String saveFileName = "save.txt";
            if (Files.exists(Paths.get(saveFileName))) {
                try (BufferedReader fin = new BufferedReader(new FileReader(new File(saveFileName)))) {
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = fin.readLine()) != null) builder.append(line);
                    save = gson.fromJson(builder.toString(), Save.class);
                    if (save != null) {
                        list = save.getUsers();
                    }
                } catch (Exception e) {}
            }
            PrintWriter respWriter = resp.getWriter();
            respWriter.println(htmlTableWithUsers(list));
        }
    }
    String htmlTableWithUsers(ArrayList<User> users){
        StringBuilder builder = new StringBuilder();
        String head = "  <thead>\n" +
                "  <tr>\n" +
                "    <th>#</th>\n" +
                "    <th>Login</th>\n" +
                "    <th>Password</th>\n" +
                "    <th>Gender</th>\n" +
                "    <th>Phone</th>\n" +
                "    <th>Email</th>\n" +
                "    <th>Subscribe</th>\n" +
                "  </tr>\n" +
                "  </thead>";
        builder.append("<table border=\"1\">");
        builder.append(head);
        builder.append("<tbody>");
        int number=1;
        for (User user:users) {
            builder.append(user.toHtmlTableRow(number++));
        }
        builder.append("</tbody>");
        builder.append("</table>");
        return builder.toString();
    }
}
