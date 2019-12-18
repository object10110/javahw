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

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");//Male//Female
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String sub = req.getParameter("sub");//on

        User newUser = new User(login,
                password,
                gender.equals("Male") ? Gender.Male : Gender.Female,
                phone,
                email,
                sub != null);

        String connString = "jdbc:mysql://localhost:3306/registration_db?serverTimezone=Europe/Kiev&characterEncoding=utf8";
        String userDB = "root";
        String passwordDB = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(connString, userDB, passwordDB);) {
            Statement stmt = conn.createStatement();
            String sql = "";

            sql = "SELECT `id` FROM `Registration_db`.`Gender` WHERE `name` = '" + newUser.getGender().toString() + "';";

            ResultSet result = stmt.executeQuery(sql);

            if (result.next()) {
                int gender_id = result.getInt("id");

                sql = "INSERT INTO `Registration_db`.`User`(`login`,`password`,`phone`,`email`,`subscribe`,`gender_id`) ";
                sql += "VALUES ('" + login + "', '" + password + "', '" + phone + "', '" + email + "'," + newUser.isSubscribe() + ", " + gender_id + ");";
                stmt.executeUpdate(sql);
                resp.getWriter().println("OK");
            }
        } catch (SQLException e) {
            resp.getWriter().println(e.getMessage());
        }
    }
}

