package org.itstep;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InitDatabaseServlet extends HttpServlet {

    String url;
    String user;
    String password;

    static Random rnd = new Random();
    static String[] firstNames = {"Vasja", "Masha", "Petja", "Roma", "Sima", "Jack", "Mila", "Fred", "Nicola"};

    static String[] lastNames = {"Smith", "Staunton", "Anderson"};

    static String[] genders = {"male", "female", "unknown"};

    public static final String INSERT = "INSERT INTO person(first_name, last_name, gender) VALUES(?, ?, ?)";

    public static final String SELECT_LIKE = "select * from people.person where first_name LIKE (?) AND last_name LIKE (?)";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        url = getServletContext().getInitParameter("db:url");
        user = getServletContext().getInitParameter("db:user");
        password = getServletContext().getInitParameter("db:pass");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String secondNamesName = req.getParameter("secondName");
        PrintWriter writer = resp.getWriter();

        if (firstName == null && secondNamesName == null) {
            String size = req.getParameter("size");
            int count = 100_000;
            int countThread = 10;
            try {
                count = Integer.parseInt(size);
            } catch (Throwable ex) {

            }
            iniDb(count, countThread);
        } else {
            writer.println(findPersons(firstName, secondNamesName));
        }
        //writer.println("Success");
    }

    private String findPersons(String firstName, String secondName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_LIKE);
                preparedStatement.setString(1, "%" + firstName + "%");
                preparedStatement.setString(2, "%" + secondName + "%");
            long start = System.currentTimeMillis();

            ResultSet resultSet = preparedStatement.executeQuery();
            long finish = System.currentTimeMillis();
            String result = "";
            while (resultSet.next()) {
                result += resultSet.getString("first_name") + " ";
                result += resultSet.getString("last_name");
                result+="<br>";
            }
            long timeConsumedMillis = finish - start;
            result+="time: " + timeConsumedMillis + "(ms).";
            return result;
        } catch (SQLException e) {
           return  e.getMessage();
        }

    }

    String generateFirstName() {
        return firstNames[rnd.nextInt(firstNames.length)];
    }

    String generateLastName() {
        return lastNames[rnd.nextInt(lastNames.length)];
    }

    String generateGender() {
        return genders[rnd.nextInt(genders.length)];
    }

    private void iniDb(int count, int countThread) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ExecutorService executor = Executors.newFixedThreadPool(countThread);

        for (int i = 0; i < countThread; i++) {
            executor.execute(() -> {
                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    PreparedStatement preparedStatement = conn.prepareStatement(INSERT);
                    for (int j = 0; j < count / countThread; j++) {
                        preparedStatement.setString(1, generateFirstName());
                        preparedStatement.setString(2, generateLastName());
                        preparedStatement.setString(3, generateGender());
                        preparedStatement.execute();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
