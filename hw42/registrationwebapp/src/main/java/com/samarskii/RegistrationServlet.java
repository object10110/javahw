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

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");//Male//Female
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String sub = req.getParameter("sub");//on

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

        User newUser = new User(login,
                                password,
                                gender.equals("Male")? Gender.Male: Gender.Female,
                                phone,
                                email,
                                sub!= null);

        PrintWriter respWriter = resp.getWriter();
        respWriter.println("OK");
        list.add(newUser);

        try (FileWriter writer = new FileWriter(saveFileName, false)) {
            Save newSave = new Save();
            newSave.setUsers(list);
            String text = gson.toJson(newSave);
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

