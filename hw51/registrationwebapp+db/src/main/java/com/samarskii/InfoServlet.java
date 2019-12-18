package com.samarskii;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
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
            String connString = "jdbc:mysql://localhost:3306/registration_db?serverTimezone=Europe/Kiev&characterEncoding=utf8";
            String userDB = "root";
            String passwordDB = "";
            ArrayList<User> list = new ArrayList<>();

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try (Connection conn = DriverManager.getConnection(connString, userDB, passwordDB);) {
                Statement stmt = conn.createStatement();
                String sql = "";

                sql = "SELECT * FROM `Registration_db`.`User`;";

                ResultSet result = stmt.executeQuery(sql);

                while(result.next()) {
                    int gender_id = result.getInt("gender_id");
                    String user_login = result.getString("login");
                    String user_password = result.getString("password");
                    String user_phone = result.getString("phone");
                    String user_email = result.getString("email");
                    boolean subscribe = result.getBoolean("subscribe");

                    Statement stmt2 = conn.createStatement();
                    sql = "SELECT name FROM `Registration_db`.`Gender` WHERE id =" + gender_id + ";";
                    ResultSet genderNameResult = stmt2.executeQuery(sql);
                    String genderName = "";
                    if(genderNameResult.next()){
                        genderName = genderNameResult.getString("name");
                    }
                    else{
                        genderName = "Male";//default
                    }
                    User newUser = new User(user_login, user_password, Gender.valueOf(genderName), user_phone, user_email, subscribe);
                    list.add(newUser);
                }
            } catch (SQLException e) {
                resp.getWriter().println(e.getMessage());
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
